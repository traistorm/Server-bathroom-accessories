package com.example.server.Utilities;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class Function {
    public static String tokenInitialization() // Tạo một token
    {

        while (true) // Nếu có lỗi trong quá trình khởi tạo token, sẽ lặp lại thao tác này cho tới khi khởi tạo xong nó!
        {
            try {
                String SECRET_KEY = "traistorm-key-12"; // Tạo key
                SecretKeySpec skeySpec = new SecretKeySpec(SECRET_KEY.getBytes(), "AES");
                Cipher cipher = null;
                cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
                cipher.init(Cipher.ENCRYPT_MODE, skeySpec); // Set ở chế độ mã hoá

                JSONObject jsonHeader = new JSONObject(); // Json header
                jsonHeader.put("type", "jwt");

                byte[] byteJsonHeader = cipher.doFinal(jsonHeader.toString().getBytes());
                String stringJsonHeader =  Base64.getEncoder().encodeToString(byteJsonHeader); // Mã hoá header

                JSONObject jsonPayload = new JSONObject(); // Json payload
                jsonPayload.put("username", "Test");
                jsonPayload.put("password", "TestPassword");

                byte[] byteJsonPayload = cipher.doFinal(jsonHeader.toString().getBytes());
                String stringJsonPayload =  Base64.getEncoder().encodeToString(byteJsonPayload); // Mã hoá payload
                //String payload = jsonPayload.toString();


                //System.out.println(signature.length());
                Long tokenInitializationTime = System.currentTimeMillis(); // Lấy thời gian hiện tại (millisecond)
                Long tokenExpirationTime = tokenInitializationTime + 1800000; // Thòi gian hết hạn = thời gian hiện tại + thời gian tồn tại của token (millisecond)

                //String test = BCrypt.hashpw(signature, BCrypt.gensalt(12));
                //System.out.println(test);


                //String original = "stackjava.com";

                //System.out.println(tokenExpirationTime.toString());
                byte[] byteTokenExpirationTime = cipher.doFinal(tokenExpirationTime.toString().getBytes());
                String stringTokenExpirationTime =  Base64.getEncoder().encodeToString(byteTokenExpirationTime); // Mã hoá thời gian tồn tại token

                //System.out.println(test + encrypted);

                /*byte[] byteDecrypted = cipher.doFinal(Base64.getDecoder().decode(tokenExpirationTimeValue.getBytes()));
                String decrypted = new String(byteDecrypted);*/

                //System.out.println(decrypted);

                //System.out.println(decrypted.length());

                byte[] byteSignature = cipher.doFinal((stringJsonHeader + "." + stringJsonPayload + "." + stringTokenExpirationTime).getBytes(StandardCharsets.UTF_8));
                String stringSignature =  Base64.getEncoder().encodeToString(byteSignature); // Mã hoá signature từ header + payload + thời gian tồn tại token

                return stringJsonHeader + "." + stringJsonPayload + "." + stringSignature; // Trả về token gồm header + payload + thời gian tồn tại token

            } catch (NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException |
                     InvalidKeyException e) {
                System.out.println(e);
            }
        }

    }
    // Token for test
    // "A2qJwy11cV3ffM90qJ2H4g==.lEMNuOFXvdNsNfSF08JFIs8UWlQ64oHDQcIkHzPYsvTPi1sZzr4JO3+i4rdLN4wL.sIl3hJcDwUxvfis0Kw39aT8KoF7nNq3i3P5FJMyv9aDfxnTsEUoyF8vcjy8bi1WuNQR7ZgtS2SZ3+AIZJnFrmRMV2/cMamf7n8ufw9bLJJufNpsPAVMgznwpK+TB7fBMZttMlHrqjRdWxa2KaWsRtxHOZ+80S3cHkl3tNU/+GPU="
    public static String tokenAuthentication(String tokenValue) // Giải mã, ngược quá trình tạo token
    {
        // -1 error, 0 : login info is not correct, 1: expiration,  2 : true
        try {
            // Một token chia làm 3 đoạn phân tách bởi dấu .
            String[] parts = tokenValue
                    .split("\\.");

            String stringHeaderEncrypt = parts[0];
            String stringJsonPayloadEncrypt  = parts[1];
            String stringSignatureEncrypt = parts[2];

            HttpHeaders headers = new HttpHeaders();
            //((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest().getHeader("Authorization");

            return ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest().getHeader("Authorization");

            /*stringHeaderEncrypt = stringHeaderEncrypt.replaceAll("\\s+","");
            stringJsonPayloadEncrypt = stringJsonPayloadEncrypt.replaceAll("\\s+","");
            stringSignatureEncrypt = stringSignatureEncrypt.replaceAll("\\s+","");

            String SECRET_KEY = "traistorm-key-12"; // Key để giải mã aes
            SecretKeySpec sKeySpec = new SecretKeySpec(SECRET_KEY.getBytes(), "AES");
            Cipher cipher = null;
            cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");

            cipher.init(Cipher.DECRYPT_MODE, sKeySpec);



            byte[] byteHeaderDecrypt = cipher.doFinal(Base64.getDecoder().decode(stringHeaderEncrypt.getBytes()));// Giải mã đoạn một sẽ ra header
            String stringHeaderDecrypt = new String(byteHeaderDecrypt);

            byte[] byteJsonPayloadDecrypt = cipher.doFinal(Base64.getDecoder().decode(stringJsonPayloadEncrypt.getBytes())); // Giải mã đoạn 2 sẽ ra payload, chưa thông tin đăng nhập, vai trò, ...
            String stringJsonPayloadDecrypt = new String(byteJsonPayloadDecrypt);

            byte[] byteSignatureEncrypt = cipher.doFinal(Base64.getDecoder().decode(stringSignatureEncrypt.getBytes())); // Giải mã đoạn 3 ra signature, là hợp của hai đoạn trên và thời gian tồn tại của token
            String stringSignatureDecrypt = new String(byteSignatureEncrypt);

            // Decrypt stringSignatureDecrypt we will get header + payload + token expiration time
            String[] stringSignatureDecryptSpilt = stringSignatureDecrypt.split("\\.");

            String tokenExpirationTimeEncrypt = stringSignatureDecryptSpilt[2]; // Lấy phần 3 là thời gian tồn tại của token và giải mã
            byte[] byteTokenExpirationTimeValue = cipher.doFinal(Base64.getDecoder().decode(tokenExpirationTimeEncrypt.getBytes())); // String of TokenExpirationTimeValue
            String tokenExpirationTimeDecrypt = new String(byteTokenExpirationTimeValue);
            //System.out.println(decrypted);

            // Check jwt

            // Check for payload
            try
            {
                JSONObject jsonPayloadDecrypt = new JSONObject(stringJsonPayloadDecrypt); // Nhận Json từ string được giải mã
                String username = jsonPayloadDecrypt.get("username").toString();
                String password = jsonPayloadDecrypt.get("password").toString();
                if (username.equals("") || password.equals("")) // Kiểm tra username và password
                {
                    return "0";
                }
            }
            catch (JSONException e) // Có ngoại lệ thì trả về token không hợp lệ, ngoại lệ có thể là không tồn tại key trong json,...
            {
                return "-1";
            }
            //

            // Check for token expiration
            long longTokenExpirationTimeDecrypt = Long.parseLong(tokenExpirationTimeDecrypt);
            // Token is expired
            if (longTokenExpirationTimeDecrypt >= System.currentTimeMillis()) // Kiểm tra nếu token đã hết hạn
            {
                return "2";
            }
            else
            {
                return "1";
            }


            //*/

        }
        catch (Exception e)
        {
            System.out.println(e);
            return "-1";
        }

    }
}

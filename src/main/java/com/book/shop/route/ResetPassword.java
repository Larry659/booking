package com.book.shop.route;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping(value ="/api/v1/password")

@RequiredArgsConstructor
public class ResetPassword {



//        @Autowired
//        private UserService userService;
//
//        @PostMapping("/request")
//        public ResponseEntity<String> requestPasswordReset(@RequestBody Map<String, String> emailMap) {
//            String email = emailMap.get("email");
//
//            // Generate a unique token and save it in the database with an expiration date
//            String resetToken = UUID.randomUUID().toString();
//            userService.createPasswordResetToken(email, resetToken);
//
//            // Send an email to the user with a link containing the reset token
//            sendResetEmail(email, resetToken);
//
//            return ResponseEntity.ok("Password reset instructions sent to your email.");
//        }
//
//        @PostMapping("/reset")
//        public ResponseEntity<String> resetPassword(@RequestBody PasswordResetRequest request) {
//            String resetToken = request.getResetToken();
//            String newPassword = request.getNewPassword();
//
//            // Verify the reset token
//            if (userService.isResetTokenValid(resetToken)) {
//                // Update the user's password
//                userService.resetPassword(resetToken, newPassword);
//                return ResponseEntity.ok("Password reset successfully.");
//            } else {
//                return ResponseEntity.ok("Invalid or expired reset token.");
//            }
//        }
    }




package Control;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Controller_VerifyOTP {

    @FXML
    private TextField TextField_OTP;

    private String soDienThoai; // ✅ Nhận từ màn hình trước (ForgotPassword)

    public void setSoDienThoai(String sdt) {
        this.soDienThoai = sdt;
    }

    @FXML
    private void Xac_Nhan_OTP(ActionEvent event) throws IOException {
        String otp = TextField_OTP.getText().trim();

        if (otp.isEmpty()) {
            showAlert("Thông báo", "Vui lòng nhập mã OTP!");
            return;
        }

        // ⚠️ Giả lập OTP đúng là "123456"
        if (otp.equals("123456")) {
            System.out.println("Xác nhận OTP cho SDT: " + soDienThoai); // ✅ test

            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/View/ResetPassword.fxml")));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Đặt lại mật khẩu");
            stage.show();
        } else {
            showAlert("Lỗi", "Mã OTP không đúng!");
        }
    }

    @FXML
    private void handleBack(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/View/ForgotPassword.fxml")));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Quên mật khẩu");
        stage.show();
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }
}

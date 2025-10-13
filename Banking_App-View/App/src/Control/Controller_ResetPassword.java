package Control;

import DAO.NguoiDungDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Controller_ResetPassword {

    @FXML
    private PasswordField PasswordField_NhapMatKhau;
    @FXML
    private PasswordField PasswordField_XacThucMatKhau;

    @FXML
    public void Cap_Nhat_Mat_Khau(ActionEvent event) throws IOException {
        String mk1 = PasswordField_NhapMatKhau.getText();
        String mk2 = PasswordField_XacThucMatKhau.getText();

        if (mk1.isEmpty() || mk2.isEmpty()) {
            showAlert("Thông báo", "Vui lòng nhập đầy đủ mật khẩu!");
            return;
        }

        if (!mk1.equals(mk2)) {
            showAlert("Lỗi", "Mật khẩu xác nhận không khớp!");
            return;
        }

        String sdt = Controller_ForgotPassword.getSoDienThoaiNhap();
        if (sdt == null) {
            showAlert("Lỗi", "Không có thông tin số điện thoại!");
            return;
        }

        NguoiDungDAO dao = new NguoiDungDAO();
        dao.updatePasswordByPhone(sdt, mk1);

        showAlert("Thành công", "Đặt lại mật khẩu thành công!");

        // ✅ Quay lại giao diện đăng nhập
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/View/Login.fxml")));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Đăng nhập");
        stage.show();
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }
}

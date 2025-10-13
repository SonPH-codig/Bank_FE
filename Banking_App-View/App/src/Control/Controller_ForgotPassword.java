package Control;

import DAO.NguoiDungDAO;
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
import java.sql.SQLException;
import java.util.Objects;

public class Controller_ForgotPassword {

    @FXML
    private TextField TextField_SoDienThoai;

    private static String soDienThoaiNhap; // lưu tạm để chuyển qua giao diện đặt lại mật khẩu

    public static String getSoDienThoaiNhap() {
        return soDienThoaiNhap;
    }

    @FXML
    public void Gui_Ma_Xac_Nhan(ActionEvent event) throws IOException {
        String sdt = TextField_SoDienThoai.getText().trim();
        NguoiDungDAO dao = new NguoiDungDAO();

        if (sdt.isEmpty()) {
            showAlert("Thông báo", "Vui lòng nhập số điện thoại!");
            return;
        }

        try {
            if (dao.Ton_Tai_Nguoi_Dung(sdt)) {
                soDienThoaiNhap = sdt;

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/VerifyOTP.fxml"));
                Parent root = loader.load();

//  Truyền số điện thoại sang VerifyOTP
                Controller_VerifyOTP controller = loader.getController();
                controller.setSoDienThoai(sdt);

                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.setTitle("Xác nhận mã OTP");
                stage.show();
            } else {
                showAlert("Lỗi", "Số điện thoại không tồn tại trong hệ thống!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void handleBack(ActionEvent event) throws IOException {
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

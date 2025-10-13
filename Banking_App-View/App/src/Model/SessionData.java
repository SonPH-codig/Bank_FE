package Model;

public class SessionData {
    private static String soDienThoai;
    private static String matKhau;

    public static String getSoDienThoai() {
        return soDienThoai;
    }

    public static void setSoDienThoai(String soDienThoai) {
        SessionData.soDienThoai = soDienThoai;
    }

    public static String getMatKhau() {
        return matKhau;
    }

    public static void setMatKhau(String matKhau) {
        SessionData.matKhau = matKhau;
    }

    public static void clear() {
        soDienThoai = null;
        matKhau = null;
    }
}

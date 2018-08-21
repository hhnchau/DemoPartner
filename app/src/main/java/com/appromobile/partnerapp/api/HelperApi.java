package com.appromobile.partnerapp.api;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Chau Huynh on 03/03/02017.
 */

public class HelperApi {

    public static boolean restResult(int r) {
        boolean result = false;
        switch (r) {
            case 0: //Error
                result = false;
                Log.d("LOG_IN_VIA_MOBILE", "--> Error <--");
                break;
            case 1: //OK
                result = true;
                Log.d("LOG_IN_VIA_MOBILE", "--> OK <--");
                break;
            case 2: //LoginFail
                result = false;
                Log.d("LOG_IN_VIA_MOBILE", "--> Fail <--");
                break;
            case 3: //No Permission
                result = false;
                Log.d("LOG_IN_VIA_MOBILE", "--> No Permission <--");
                break;
            case 5: //UnAuthorized
                result = false;
                Log.d("LOG_IN_VIA_MOBILE", "--> UnAuthorized <--");
                break;
        }
        return result;
    }


    public static String responseOk(int i) {
        String status = "";
        switch (i) {
            case 0:
                status = "Không thành công";
                return status;
            case 1:
                status = "Thành công";
                return status;
            case 2:
                status = "Không có giá trị";
                return status;
            case 3:
                status = "Đã tồn tại";
                return status;
            case 4:
                status = "Nhập lại số điện thoại";
                return status;
            case 5:
                status = "Nhập lại tên";
                return status;
            case 6:
                status = "Nhập lại mật khẩu, mật khẩu gồm 6 kí tự";
                return status;
            case 7:
                status = "Nhập lại email";
                return status;
            case 8:
                status = "ID không đúng";
                return status;
            case 9:
                status = "Kiểu dữ liệu không đúng";
                return status;
            case 10:
                status = "Tài khoản đã được đăng nhập bởi người khác";
                return status;
            case 11:
                status = "Nhập lại Passcode";
                return status;
            case 12:
                status = "Không tìm thấy người dùng";
                return status;
            case 13:
                status = "Mật khẩu cũ không đúng";
                return status;
            case 14:
                status = "Mật khẩu nhập lại không đúng";
                return status;
            case 15:
                status = "Sai kiểu giới tính";
                return status;
            case 16:
                status = "Mã khuyến mãi của bạn không có trong hệ thống.";
                return status;
            case 17:
                status = "Mã khuyến mãi của bạn đã hết hạn sử dụng.";
                return status;
            case 18:
                status = "Mã khuyến mãi của bạn đã được sử dụng.";
                return status;
        }
        return status;
    }

    public static String responseFail(int i) {
        String status = "";
        switch (i) {
            case 0:
                status = "Không thành công";
                return status;
            case 1:
                status = "Thành công";
                return status;
            case 2:
                status = "Không có giá trị";
                return status;
            case 3:
                status = "Đã tồn tại";
                return status;
            case 4:
                status = "Nhập lại số điện thoại";
                return status;
            case 5:
                status = "Nhập lại tên";
                return status;
            case 6:
                status = "Nhập lại mật khẩu, mật khẩu gồm 6 kí tự";
                return status;
            case 7:
                status = "Nhập lại email";
                return status;
            case 8:
                status = "ID không đúng";
                return status;
            case 9:
                status = "Kiểu dữ liệu không đúng";
                return status;
            case 10:
                status = "Tài khoản đã được đăng nhập bởi người khác";
                return status;
            case 11:
                status = "Nhập lại Passcode";
                return status;
            case 12:
                status = "Không tìm thấy người dùng";
                return status;
            case 13:
                status = "Mật khẩu cũ không đúng";
                return status;
            case 14:
                status = "Mật khẩu nhập lại không đúng";
                return status;
            case 15:
                status = "Sai kiểu giới tính";
                return status;
            case 16:
                status = "Mã khuyến mãi của bạn không có trong hệ thống.";
                return status;
            case 17:
                status = "Mã khuyến mãi của bạn đã hết hạn sử dụng.";
                return status;
            case 18:
                status = "Mã khuyến mãi của bạn đã được sử dụng.";
                return status;
        }
        return status;
    }
}

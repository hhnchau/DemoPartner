package com.appromobile.partnerapp.presenter.view;

import com.appromobile.partnerapp.model.api.ResSettingForm;

/**
 * Created by Chau Huynh on 3/31/2017.
 */

public interface ViewSetting {
    void findReservationSetting(ResSettingForm resSettingForm);
    void findHotelSettingForm(int numbetOfRecord);
    void updateHotelSettingFormOk();
    void updateReservationSettingOk();
    void updateCancelledSettingOk();
    void updateAutoReservedOk();

}

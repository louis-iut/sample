package com.amiltone.ci.model.manager;

import com.amiltone.ci.model.pojo.BLEDevice;
import com.amiltone.ci.model.pojo.BLEDeviceMockFailureImpl;
import com.amiltone.ci.model.pojo.BLEDeviceMockImpl;

/**
 * Created by amiltonedev_lt027 on 01/11/2016.
 */
public class BLEManager {
    private int test;
    private static BLEManager ourInstance = new BLEManager();
    public static BLEManager getInstance() {
        return ourInstance;
    }

    private BLEDevice currentDevice;

    // Change injected class to test errors : BLEDeviceMockFailureImpl
    private BLEManager() {
        currentDevice = new BLEDeviceMockImpl();
    }

    public boolean connectDevice() {
        if (isConnected() || isOffline()) {
            return false;
        }
        return currentDevice.pairConnectDevice();
    }

    public String getBLELastValue() {
        if (!isConnected()) {
            return null;
        }
        return "Last value : " + currentDevice.getLastValue();
    }

    public String getBLELastValues() {
        if (!isConnected()) {
            return null;
        }
        String result = "Last values : \n";
        for (String bleValue : currentDevice.getLastValues()) {
            result+=bleValue+"\n";
        }
        return result;
    }

    private boolean isConnected() {
        return currentDevice.getStatus() == BLEDeviceMockImpl.CONNECTED;
    }

    private boolean isOffline() {
        return currentDevice.getStatus() == BLEDeviceMockImpl.OFFLINE;
    }
}

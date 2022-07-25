package thales.mobile.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SingleEmployeeAPIResponse {

    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("data")
    @Expose
    Employee data = null;

    @SerializedName("message")
    @Expose
    String message;

    public String getStatus() {
        return status;
    }

    public Employee getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }
}

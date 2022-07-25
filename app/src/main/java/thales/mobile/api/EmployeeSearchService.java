package thales.mobile.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import thales.mobile.model.EmployeeAPIResponse;
import thales.mobile.model.SingleEmployeeAPIResponse;

public interface EmployeeSearchService {
    @GET("/api/v1/employees")
    Call<EmployeeAPIResponse> getAllEmployees();
    @GET("/api/v1/employee/{id}")
    Call<SingleEmployeeAPIResponse> searchEmployees(
            @Path("id") int id
    );
}
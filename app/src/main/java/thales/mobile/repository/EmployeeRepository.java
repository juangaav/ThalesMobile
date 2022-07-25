package thales.mobile.repository;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;
import thales.mobile.api.EmployeeSearchService;
import thales.mobile.model.EmployeeAPIResponse;
import thales.mobile.model.SingleEmployeeAPIResponse;

public class EmployeeRepository {
    private static final String EMPLOYEE_SEARCH_SERVICE_BASE_URL = "https://dummy.restapiexample.com/";

    private final EmployeeSearchService employeeSearchService;
    private final MutableLiveData<EmployeeAPIResponse> employeeResponseLiveData;
    private final MutableLiveData<SingleEmployeeAPIResponse> singleEmployeeResponseLiveData;


    public EmployeeRepository() {
        employeeResponseLiveData = new MutableLiveData<>();
        singleEmployeeResponseLiveData = new MutableLiveData<>();

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        employeeSearchService = new retrofit2.Retrofit.Builder()
                .baseUrl(EMPLOYEE_SEARCH_SERVICE_BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(EmployeeSearchService.class);

    }

    public void getAllEmployees(){
        employeeSearchService.getAllEmployees()
                .enqueue(new Callback<EmployeeAPIResponse>() {
                    @Override
                    public void onResponse(@NonNull Call<EmployeeAPIResponse> call, @NonNull Response<EmployeeAPIResponse> response) {
                        employeeResponseLiveData.postValue(response.body());
                    }

                    @Override
                    public void onFailure(@NonNull Call<EmployeeAPIResponse> call, @NonNull Throwable t) {
                        employeeResponseLiveData.postValue(null);
                    }

                });
    }

    public void searchEmployees(int id){
        employeeSearchService.searchEmployees(id)
                .enqueue(new Callback<SingleEmployeeAPIResponse>() {
                    @Override
                    public void onResponse(@NonNull Call<SingleEmployeeAPIResponse> call, @NonNull Response<SingleEmployeeAPIResponse> response) {
                        singleEmployeeResponseLiveData.postValue(response.body());
                    }

                    @Override
                    public void onFailure(@NonNull Call<SingleEmployeeAPIResponse> call, @NonNull Throwable t) {
                        singleEmployeeResponseLiveData.postValue(null);
                    }
                });
    }

    public LiveData<EmployeeAPIResponse> getEmployeeResponseLiveData() {
        return employeeResponseLiveData;
    }

    public MutableLiveData<SingleEmployeeAPIResponse> getSingleEmployeeResponseLiveData() {
        return singleEmployeeResponseLiveData;
    }
}

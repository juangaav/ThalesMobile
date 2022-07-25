package thales.mobile.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import thales.mobile.model.EmployeeAPIResponse;
import thales.mobile.model.SingleEmployeeAPIResponse;
import thales.mobile.repository.EmployeeRepository;

public class EmployeeSearchViewModel extends AndroidViewModel {
    private EmployeeRepository employeeRepository;
    private LiveData<EmployeeAPIResponse> employeeResponseLiveData;
    private LiveData<SingleEmployeeAPIResponse> singleEmployeeResponseLiveData;

    public EmployeeSearchViewModel(@NonNull Application application) {
        super(application);
    }

    public void init() {
        employeeRepository = new EmployeeRepository();
        employeeResponseLiveData = employeeRepository.getEmployeeResponseLiveData();
        singleEmployeeResponseLiveData = employeeRepository.getSingleEmployeeResponseLiveData();
    }

    public void lookupEmployee(int id) {
        employeeRepository.searchEmployees(id);
    }

    public void getAllEmployees(){
        employeeRepository.getAllEmployees();
    }

    public LiveData<EmployeeAPIResponse> getEmployeeResponseLiveData() {
        return employeeResponseLiveData;
    }

    public LiveData<SingleEmployeeAPIResponse> getSingleEmployeeResponseLiveData() {
        return singleEmployeeResponseLiveData;
    }

}

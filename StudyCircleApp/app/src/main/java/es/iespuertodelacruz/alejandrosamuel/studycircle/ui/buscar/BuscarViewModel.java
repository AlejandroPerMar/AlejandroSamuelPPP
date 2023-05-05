package es.iespuertodelacruz.alejandrosamuel.studycircle.ui.buscar;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class BuscarViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public BuscarViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Buscar fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
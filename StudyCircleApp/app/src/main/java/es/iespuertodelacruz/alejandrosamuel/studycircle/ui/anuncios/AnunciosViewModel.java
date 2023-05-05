package es.iespuertodelacruz.alejandrosamuel.studycircle.ui.anuncios;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AnunciosViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public AnunciosViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is anuncios fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
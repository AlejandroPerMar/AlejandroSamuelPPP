package es.iespuertodelacruz.alejandrosamuel.studycircle.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;


import java.util.List;

import es.iespuertodelacruz.alejandrosamuel.studycircle.data.rest.dto.AlumnoDTO;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.rest.dto.MateriaDTO;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.rest.dto.UsuarioLoginDTO;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.rest.dto.UsuarioRegisterDTO;
import es.iespuertodelacruz.alejandrosamuel.studycircle.repository.AuthRepository;
import es.iespuertodelacruz.alejandrosamuel.studycircle.repository.CursosRepository;
import es.iespuertodelacruz.alejandrosamuel.studycircle.repository.ProfilesConfRepository;

public class MainActivityViewModel extends AndroidViewModel {
    private final AuthRepository authRepository;

    private final ProfilesConfRepository profilesConfRepository;

    private final CursosRepository cursosRepository;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        authRepository = new AuthRepository(application);
        profilesConfRepository = new ProfilesConfRepository(application);
        this.cursosRepository = new CursosRepository(application);
    }

    public LiveData<String> getLiveDataToken(UsuarioLoginDTO usuarioLoginDTO) {
        return authRepository.getAuthToken(usuarioLoginDTO);
    }

    public LiveData<Object> register(UsuarioRegisterDTO usuarioRegisterDTO) {
        return authRepository.registerUsuario(usuarioRegisterDTO);
    }

    public LiveData<String> resendEmail(String token) {
        return authRepository.resendEmail(token);
    }

    public LiveData<Object> createStudentProfile(AlumnoDTO alumnoDTO, String token) {
        return profilesConfRepository.createStudentProfile(alumnoDTO, token);
    }

    public LiveData<Object> createTutorProfile(List<MateriaDTO> materias, String token) {
        return profilesConfRepository.createTutorProfile(materias, token);
    }

    public LiveData<Object> addAlumnoToCurso(Integer idCurso, Integer idAlumno, String token) {
        return cursosRepository.addAlumnoToCurso(idCurso, idAlumno, token);
    }

    public LiveData<Object> removeAlumnoToCurso(Integer idCurso, Integer idAlumno, String token) {
        return cursosRepository.removeAlumnoToCurso(idCurso, idAlumno, token);
    }

    public LiveData<Object> findCursosTutor(String token) {
        return cursosRepository.findCursosTutor(token);
    }

    public LiveData<Object> changeTituloCurso(Integer idCurso, String titulo, String token) {
        return cursosRepository.changeTituloCurso(idCurso, titulo, token);
    }
}

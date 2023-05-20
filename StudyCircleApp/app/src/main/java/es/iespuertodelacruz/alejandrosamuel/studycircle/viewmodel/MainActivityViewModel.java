package es.iespuertodelacruz.alejandrosamuel.studycircle.viewmodel;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import es.iespuertodelacruz.alejandrosamuel.studycircle.data.rest.dto.ActividadDTO;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.rest.dto.AlumnoDTO;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.rest.dto.AmistadDTO;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.rest.dto.AnuncioDTO;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.rest.dto.CursoDTO;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.rest.dto.EventoCalendarioDTO;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.rest.dto.MateriaDTO;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.rest.dto.TutorDTO;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.rest.dto.UsuarioLoginDTO;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.rest.dto.UsuarioRegisterDTO;
import es.iespuertodelacruz.alejandrosamuel.studycircle.repository.ActividadesRepository;
import es.iespuertodelacruz.alejandrosamuel.studycircle.repository.AlertasRepository;
import es.iespuertodelacruz.alejandrosamuel.studycircle.repository.AmistadesRepository;
import es.iespuertodelacruz.alejandrosamuel.studycircle.repository.AnunciosRepository;
import es.iespuertodelacruz.alejandrosamuel.studycircle.repository.AuthRepository;
import es.iespuertodelacruz.alejandrosamuel.studycircle.repository.AuthTokenRepository;
import es.iespuertodelacruz.alejandrosamuel.studycircle.repository.CursosRepository;
import es.iespuertodelacruz.alejandrosamuel.studycircle.repository.EventosCalendarioRepository;
import es.iespuertodelacruz.alejandrosamuel.studycircle.repository.MateriasRepository;
import es.iespuertodelacruz.alejandrosamuel.studycircle.repository.NivelesEstudiosRepository;
import es.iespuertodelacruz.alejandrosamuel.studycircle.repository.ProfilesConfRepository;

public class MainActivityViewModel extends AndroidViewModel {
    private final AuthRepository authRepository;

    private final AuthTokenRepository authTokenRepository;

    private final ProfilesConfRepository profilesConfRepository;

    private final CursosRepository cursosRepository;

    private final MateriasRepository materiasRepository;

    private final AnunciosRepository anunciosRepository;

    private final EventosCalendarioRepository eventosCalendarioRepository;

    private final ActividadesRepository actividadesRepository;

    private final AlertasRepository alertasRepository;

    private final AmistadesRepository amistadesRepository;

    private final NivelesEstudiosRepository nivelesEstudiosRepository;

    private String registerSuccessMessage;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        authRepository = new AuthRepository(application);
        profilesConfRepository = new ProfilesConfRepository(application);
        this.cursosRepository = new CursosRepository(application);
        this.materiasRepository = new MateriasRepository(application);
        this.anunciosRepository = new AnunciosRepository(application);
        this.eventosCalendarioRepository = new EventosCalendarioRepository(application);
        this.actividadesRepository = new ActividadesRepository(application);
        this.alertasRepository = new AlertasRepository(application);
        this.amistadesRepository = new AmistadesRepository(application);
        this.nivelesEstudiosRepository = new NivelesEstudiosRepository(application);
        this.authTokenRepository = new AuthTokenRepository();
    }


    //Acciones de autenticación
    public LiveData<Object> getLiveDataToken(UsuarioLoginDTO usuarioLoginDTO) {
        return authRepository.getAuthToken(usuarioLoginDTO);
    }

    public LiveData<Object> register(UsuarioRegisterDTO usuarioRegisterDTO) {
        return authRepository.registerUsuario(usuarioRegisterDTO);
    }

    public LiveData<Object> resendEmail(String token) {
        return authRepository.resendEmail(token);
    }

    public LiveData<Object> getEstadoUsuario(String token) {
        return authRepository.getEstadoUsuario(token);
    }

    public void guardarTokenSharedPreferences(Context context, String token) {
        authTokenRepository.guardarTokenSharedPreferences(context, token);
    }

    public String recuperarTokenSharedPreferences(Context context) {
        return authTokenRepository.recuperarTokenSharedPreferences(context);
    }

    public void limpiarTokenSharedPreferences(Context context) {
        authTokenRepository.limpiarTokenSharedPreferences(context);
    }

    //Acciones de configuración de perfiles
    public LiveData<Object> createStudentProfile(AlumnoDTO alumnoDTO, String token) {
        return profilesConfRepository.createStudentProfile(alumnoDTO, token);
    }

    public LiveData<Object> updateAlumno(AlumnoDTO alumnoDTO, String token) {
        return profilesConfRepository.updateAlumno(alumnoDTO, token);
    }

    public LiveData<Object> getAlumno(String token) {
        return profilesConfRepository.getAlumno(token);
    }

    public LiveData<Object> createTutorProfile(List<MateriaDTO> materias, String token) {
        return profilesConfRepository.createTutorProfile(materias, token);
    }

    public LiveData<Object> updateTutor(TutorDTO tutorDTO, String token) {
        return profilesConfRepository.updateTutor(tutorDTO, token);
    }

    public LiveData<Object> getTutor(String token) {
        return profilesConfRepository.getTutor(token);
    }

    //Acciones de los cursos
    public LiveData<Object> addAlumnoToCurso(Integer idCurso, Integer idAlumno, String token) {
        return cursosRepository.addAlumnoToCurso(idCurso, idAlumno, token);
    }

    public LiveData<Object> removeAlumnoFromCurso(Integer idCurso, Integer idAlumno, String token) {
        return cursosRepository.removeAlumnoFromCurso(idCurso, idAlumno, token);
    }

    public LiveData<Object> findCursosAlumno(String token) {
        return cursosRepository.findCursosAlumno(token);
    }

    public LiveData<Object> findCursosTutor(String token) {
        return cursosRepository.findCursosTutor(token);
    }

    public LiveData<Object> changeTituloCurso(Integer idCurso, String titulo, String token) {
        return cursosRepository.changeTituloCurso(idCurso, titulo, token);
    }

    public LiveData<Object> createCurso(CursoDTO cursoDTO, String token) {
        return cursosRepository.createCurso(cursoDTO, token);
    }

    //Acciones de las materias
    public LiveData<Object> findMateriasByTutor(String token) {
        return materiasRepository.findMateriasByTutor(token);
    }

    public LiveData<Object> findMateriaById(Integer idMateria, String token) {
        return materiasRepository.findById(idMateria, token);
    }

    public LiveData<Object> findMateriasByNivelEstudios(Integer idNivelEstudios, String token) {
        return materiasRepository.findByNivelEstudiosId(idNivelEstudios, token);
    }

    //Acciones de los anuncios
    public LiveData<Object> findAnuncioById(Integer idAnuncio, String token) {
        return anunciosRepository.findAnuncioById(idAnuncio, token);
    }

    public LiveData<Object> findAllAnuncios(String token) {
        return anunciosRepository.findAnuncios(token);
    }

    public LiveData<Object> findAnunciosByUsuario(String token) {
        return anunciosRepository.findAnunciosByUsuario(token);
    }

    public LiveData<Object> crearAnuncio(AnuncioDTO anuncioDTO, String token) {
        return anunciosRepository.crearAnuncio(anuncioDTO, token);
    }

    //Acciones de los eventos de calendario
    public LiveData<Object> createEventoCalendario(EventoCalendarioDTO eventoCalendarioDTO, String token) {
        return eventosCalendarioRepository.createEventoCalendario(eventoCalendarioDTO, token);
    }

    public LiveData<Object> findByPerfilUsuarioTutor(Integer idUsuario, String token) {
        return eventosCalendarioRepository.findByPerfilUsuarioTutor(idUsuario, token);
    }

    public LiveData<Object> findByPerfilUsuarioAlumno(Integer idUsuario, String token) {
        return eventosCalendarioRepository.findByPerfilUsuarioAlumno(idUsuario, token);
    }

    //Acciones de las actividades
    public LiveData<Object> findActividadById(Integer id, String token) {
        return actividadesRepository.findActividadById(id, token);
    }

    public LiveData<Object> crearActividad(ActividadDTO actividadDTO, String token) {
        return actividadesRepository.createActividad(actividadDTO, token);
    }

    public LiveData<Object> updateActividad(ActividadDTO actividadDTO, String token) {
        return actividadesRepository.updateActividad(actividadDTO, token);
    }

    public LiveData<Object> eliminarActividadById(Integer id, String token) {
        return actividadesRepository.deleteActividad(id, token);
    }

    //Acciones de las alertas
    public LiveData<Object> findAlertasActividadByUsuario(String token) {
        return alertasRepository.findAlertasActividadByUsuario(token);
    }

    public LiveData<Object> findAlertasAmistadByUsuario(String token) {
        return alertasRepository.findAlertasAmistadByUsuario(token);
    }

    //Acciones de las amistades
    public LiveData<Object> solicitarAmistad(AmistadDTO amistadDTO, String token) {
        return amistadesRepository.solicitarAmistad(amistadDTO, token);
    }

    public LiveData<Object> findAmistadById(Integer id, String token) {
        return amistadesRepository.findAmistadById(id, token);
    }

    public LiveData<Object> findAmistadesByUsuario(String token) {
        return amistadesRepository.findAmistadesByUsuario(token);
    }

    public LiveData<Object> aceptarAmistad(Integer id, String token) {
        return amistadesRepository.aceptarAmistad(id, token);
    }

    public LiveData<Object> eliminarAmistad(Integer id, String token) {
        return amistadesRepository.eliminarAmistad(id, token);
    }

    //Acciones de los niveles de estudio
    public LiveData<Object> findAllNivelesEstudios(String token) {
        return nivelesEstudiosRepository.findAllNivelesEstudios(token);
    }

    public String getRegisterSuccessMessage() {
        return registerSuccessMessage;
    }

    public void setRegisterSuccessMessage(String registerSuccessMessage) {
        this.registerSuccessMessage = registerSuccessMessage;
    }
}

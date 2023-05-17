package es.iespuertodelacruz.alejandrosamuel.studycircle.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
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
import es.iespuertodelacruz.alejandrosamuel.studycircle.repository.CursosRepository;
import es.iespuertodelacruz.alejandrosamuel.studycircle.repository.EventosCalendarioRepository;
import es.iespuertodelacruz.alejandrosamuel.studycircle.repository.MateriasRepository;
import es.iespuertodelacruz.alejandrosamuel.studycircle.repository.NivelesEstudiosRepository;
import es.iespuertodelacruz.alejandrosamuel.studycircle.repository.ProfilesConfRepository;

public class MainActivityViewModel extends AndroidViewModel {
    private final AuthRepository authRepository;

    private final ProfilesConfRepository profilesConfRepository;

    private final CursosRepository cursosRepository;

    private final MateriasRepository materiasRepository;

    private final AnunciosRepository anunciosRepository;

    private final EventosCalendarioRepository eventosCalendarioRepository;

    private final ActividadesRepository actividadesRepository;

    private final AlertasRepository alertasRepository;

    private final AmistadesRepository amistadesRepository;

    private final NivelesEstudiosRepository nivelesEstudiosRepository;

    private LiveData<Object> responseLiveData;

    public LiveData<Object> getResponseLiveData() {
        return responseLiveData;
    }

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
    }


    //Acciones de autenticación
    public LiveData<Object> getLiveDataToken(UsuarioLoginDTO usuarioLoginDTO) {
        LiveData<Object> authToken = authRepository.getAuthToken(usuarioLoginDTO);
        responseLiveData = authToken;
        return authToken;
    }

    public LiveData<Object> register(UsuarioRegisterDTO usuarioRegisterDTO) {
        LiveData<Object> register = authRepository.registerUsuario(usuarioRegisterDTO);
        responseLiveData = register;
        return register;
    }

    public LiveData<Object> resendEmail(String token) {
        LiveData<Object> resendEmail = authRepository.resendEmail(token);
        responseLiveData = resendEmail;
        return resendEmail;
    }

    //Acciones de configuración de perfiles
    public LiveData<Object> createStudentProfile(AlumnoDTO alumnoDTO, String token) {
        LiveData<Object> createStudentProfile = profilesConfRepository.createStudentProfile(alumnoDTO, token);
        responseLiveData = createStudentProfile;
        return createStudentProfile;
    }

    public LiveData<Object> updateAlumno(AlumnoDTO alumnoDTO, String token) {
        LiveData<Object> updateAlumno = profilesConfRepository.updateAlumno(alumnoDTO, token);
        responseLiveData = updateAlumno;
        return updateAlumno;
    }

    public LiveData<Object> getAlumno(String token) {
        LiveData<Object> getAlumno = profilesConfRepository.getAlumno(token);
        responseLiveData = getAlumno;
        return getAlumno;
    }

    public LiveData<Object> createTutorProfile(List<MateriaDTO> materias, String token) {
        LiveData<Object> createTutorProfile = profilesConfRepository.createTutorProfile(materias, token);
        responseLiveData = createTutorProfile;
        return createTutorProfile;
    }

    public LiveData<Object> updateTutor(TutorDTO tutorDTO, String token) {
        LiveData<Object> updateTutor = profilesConfRepository.updateTutor(tutorDTO, token);
        responseLiveData = updateTutor;
        return updateTutor;
    }

    public LiveData<Object> getTutor(String token) {
        LiveData<Object> getTutor = profilesConfRepository.getTutor(token);
        responseLiveData = getTutor;
        return getTutor;
    }

    //Acciones de los cursos
    public LiveData<Object> addAlumnoToCurso(Integer idCurso, Integer idAlumno, String token) {
        LiveData<Object> addAlumnoToCurso = cursosRepository.addAlumnoToCurso(idCurso, idAlumno, token);
        responseLiveData = addAlumnoToCurso;
        return addAlumnoToCurso;
    }

    public LiveData<Object> removeAlumnoFromCurso(Integer idCurso, Integer idAlumno, String token) {
        LiveData<Object> removeAlumnoFromCurso = cursosRepository.removeAlumnoFromCurso(idCurso, idAlumno, token);
        responseLiveData = removeAlumnoFromCurso;
        return removeAlumnoFromCurso;
    }

    public LiveData<Object> findCursosAlumno(String token) {
        LiveData<Object> findCursosAlumno = cursosRepository.findCursosAlumno(token);
        responseLiveData = findCursosAlumno;
        return findCursosAlumno;
    }

    public LiveData<Object> findCursosTutor(String token) {
        LiveData<Object> findCursosTutor = cursosRepository.findCursosTutor(token);
        responseLiveData = findCursosTutor;
        return findCursosTutor;
    }

    public LiveData<Object> changeTituloCurso(Integer idCurso, String titulo, String token) {
        LiveData<Object> changeTituloCurso = cursosRepository.changeTituloCurso(idCurso, titulo, token);
        responseLiveData = changeTituloCurso;
        return changeTituloCurso;
    }

    public LiveData<Object> createCurso(CursoDTO cursoDTO, String token) {
        LiveData<Object> createCurso = cursosRepository.createCurso(cursoDTO, token);
        responseLiveData = createCurso;
        return createCurso;
    }

    //Acciones de las materias
    public LiveData<Object> findMateriasByTutor(String token) {
        LiveData<Object> findMateriasByTutor = materiasRepository.findMateriasByTutor(token);
        responseLiveData = findMateriasByTutor;
        return findMateriasByTutor;
    }

    public LiveData<Object> findMateriaById(Integer idMateria, String token) {
        LiveData<Object> findMateriabyId = materiasRepository.findById(idMateria, token);
        responseLiveData = findMateriabyId;
        return findMateriabyId;
    }

    public LiveData<Object> findMateriasByNivelEstudios(Integer idNivelEstudios, String token) {
        LiveData<Object> findMateriasByNivelEstudios = materiasRepository.findByNivelEstudiosId(idNivelEstudios, token);
        responseLiveData = findMateriasByNivelEstudios;
        return findMateriasByNivelEstudios;
    }

    //Acciones de los anuncios
    public LiveData<Object> findAnuncioById(Integer idAnuncio, String token) {
        LiveData<Object> findAnuncioById = anunciosRepository.findAnuncioById(idAnuncio, token);
        responseLiveData = findAnuncioById;
        return findAnuncioById;
    }

    public LiveData<Object> findAllAnuncios(String token) {
        LiveData<Object> findAllAnuncios = anunciosRepository.findAnuncios(token);
        responseLiveData = findAllAnuncios;
        return findAllAnuncios;
    }

    public LiveData<Object> findAnunciosByUsuario(String token) {
        LiveData<Object> findAnunciosByUsuario = anunciosRepository.findAnunciosByUsuario(token);
        responseLiveData = findAnunciosByUsuario;
        return findAnunciosByUsuario;
    }

    public LiveData<Object> crearAnuncio(AnuncioDTO anuncioDTO, String token) {
        LiveData<Object> crearAnuncio = anunciosRepository.crearAnuncio(anuncioDTO, token);
        responseLiveData = crearAnuncio;
        return crearAnuncio;
    }

    //Acciones de los eventos de calendario
    public LiveData<Object> createEventoCalendario(EventoCalendarioDTO eventoCalendarioDTO, String token) {
        LiveData<Object> createEventoCalendario = eventosCalendarioRepository.createEventoCalendario(eventoCalendarioDTO, token);
        responseLiveData = createEventoCalendario;
        return createEventoCalendario;
    }

    public LiveData<Object> findByPerfilUsuarioTutor(Integer idUsuario, String token) {
        LiveData<Object> findByPerfilUsuarioTutor = eventosCalendarioRepository.findByPerfilUsuarioTutor(idUsuario, token);
        responseLiveData = findByPerfilUsuarioTutor;
        return findByPerfilUsuarioTutor;
    }

    public LiveData<Object> findByPerfilUsuarioAlumno(Integer idUsuario, String token) {
        LiveData<Object> findByPerfilUsuarioAlumno = eventosCalendarioRepository.findByPerfilUsuarioAlumno(idUsuario, token);
        responseLiveData = findByPerfilUsuarioAlumno;
        return findByPerfilUsuarioAlumno;
    }

    //Acciones de las actividades
    public LiveData<Object> findActividadById(Integer id, String token) {
        LiveData<Object> findActividadById = actividadesRepository.findActividadById(id, token);
        responseLiveData = findActividadById;
        return findActividadById;
    }

    public LiveData<Object> crearActividad(ActividadDTO actividadDTO, String token) {
        LiveData<Object> crearActividad = actividadesRepository.createActividad(actividadDTO, token);
        responseLiveData = crearActividad;
        return crearActividad;
    }

    public LiveData<Object> updateActividad(ActividadDTO actividadDTO, String token) {
        LiveData<Object> updateActividad = actividadesRepository.updateActividad(actividadDTO, token);
        responseLiveData = updateActividad;
        return updateActividad;
    }

    public LiveData<Object> eliminarActividadById(Integer id, String token) {
        LiveData<Object> eliminarActividadById = actividadesRepository.deleteActividad(id, token);
        responseLiveData = eliminarActividadById;
        return eliminarActividadById;
    }

    //Acciones de las alertas
    public LiveData<Object> findAlertasActividadByUsuario(String token) {
        LiveData<Object> findAlertasActividadByUsuario = alertasRepository.findAlertasActividadByUsuario(token);
        responseLiveData = findAlertasActividadByUsuario;
        return findAlertasActividadByUsuario;
    }

    public LiveData<Object> findAlertasAmistadByUsuario(String token) {
        LiveData<Object> findAlertasAmistadByUsuario = alertasRepository.findAlertasAmistadByUsuario(token);
        responseLiveData = findAlertasAmistadByUsuario;
        return findAlertasAmistadByUsuario;
    }

    //Acciones de las amistades
    public LiveData<Object> solicitarAmistad(AmistadDTO amistadDTO, String token) {
        LiveData<Object> solicitarAmistad = amistadesRepository.solicitarAmistad(amistadDTO, token);
        responseLiveData = solicitarAmistad;
        return solicitarAmistad;
    }

    public LiveData<Object> findAmistadById(Integer id, String token) {
        LiveData<Object> findAmistadById = amistadesRepository.findAmistadById(id, token);
        responseLiveData = findAmistadById;
        return findAmistadById;
    }

    public LiveData<Object> findAmistadesByUsuario(String token) {
        LiveData<Object> findAmistadesByUsuario = amistadesRepository.findAmistadesByUsuario(token);
        responseLiveData = findAmistadesByUsuario;
        return findAmistadesByUsuario;
    }

    public LiveData<Object> aceptarAmistad(Integer id, String token) {
        LiveData<Object> aceptarAmistad = amistadesRepository.aceptarAmistad(id, token);
        responseLiveData = aceptarAmistad;
        return aceptarAmistad;
    }

    public LiveData<Object> eliminarAmistad(Integer id, String token) {
        LiveData<Object> eliminarAmistad = amistadesRepository.eliminarAmistad(id, token);
        responseLiveData = eliminarAmistad;
        return eliminarAmistad;
    }

    //Acciones de los niveles de estudio
    public LiveData<Object> findAllNivelesEstudios(String token) {
        LiveData<Object> findAllNivelesEstudios = nivelesEstudiosRepository.findAllNivelesEstudios(token);
        responseLiveData = findAllNivelesEstudios;
        return findAllNivelesEstudios;
    }

}

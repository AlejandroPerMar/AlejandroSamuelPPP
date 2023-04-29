package es.iespuertodelacruz.alejandrosamuel.studycircle.data.db;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import es.iespuertodelacruz.alejandrosamuel.studycircle.data.db.entity.ActividadCursoEntity;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.db.entity.ActividadEntity;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.db.entity.AlertaEntity;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.db.entity.AlumnoEntity;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.db.entity.AmistadEntity;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.db.entity.AnuncioEntity;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.db.entity.CursoEntity;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.db.entity.EventoCalendarioEntity;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.db.entity.MateriaEntity;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.db.entity.MateriaTutorEntity;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.db.entity.NivelEstudiosEntity;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.db.entity.TutorEntity;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.db.entity.UsuarioEntity;

@androidx.room.Database(
        entities = {
                UsuarioEntity.class,
                TutorEntity.class,
                AlumnoEntity.class,
                ActividadCursoEntity.class,
                ActividadEntity.class,
                AlertaEntity.class,
                CursoEntity.class,
                EventoCalendarioEntity.class,
                MateriaEntity.class,
                MateriaTutorEntity.class,
                NivelEstudiosEntity.class,
                AmistadEntity.class,
                AnuncioEntity.class
        }, version = 1
        , exportSchema = false
)
@TypeConverters({
        Converters.class
})
public abstract class DatabaseStudyCircle extends RoomDatabase {

    private static volatile DatabaseStudyCircle INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static DatabaseStudyCircle getDatabase(final Context context) {
        if(Objects.isNull(INSTANCE)) {
            synchronized (DatabaseStudyCircle.class) {
                if(Objects.isNull(INSTANCE)) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            DatabaseStudyCircle.class, "studycircledb")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }

}

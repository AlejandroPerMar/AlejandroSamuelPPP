package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.config;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	public static final String ALUMNO_TAG = "Alumno";
	public static final String ACTIVIDAD_V2_TAG = "ActividadV2";
	public static final String ACTIVIDAD_V3_TAG = "ActividadV3";
	public static final String AMISTAD_V2_TAG = "AmistadV2";
	public static final String ALERTA_V2_TAG = "AlertaV2";
	public static final String EVENTO_CALENDARIO_V2_TAG = "EventoCalendariov2";
	public static final String ANUNCIO_V2_TAG = "AnuncioV2";
	public static final String MATERIA_V2_TAG = "MateriaV2";
	public static final String MATERIA_V3_TAG = "MateriaV3";
	public static final String NIVEL_ESTUDIOS_V2_TAG = "NivelEstudiosV2";
	public static final String NIVEL_ESTUDIOS_V3_TAG = "NivelEstudiosV3";
	public static final String TUTOR_V2_TAG = "TutorV2";
	public static final String CURSO_V2_TAG = "CursoV2";
	public static final String LOGIN_TAG = "Login";
	public static final String REGISTER_TAG = "Register";
	
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary"))
                .paths(PathSelectors.any())
                .build()
                .tags(new Tag(ALUMNO_TAG, "Controlador REST para las acciones relacionadas con las entidades Alumno"),
                		new Tag(ACTIVIDAD_V2_TAG, "Controlador REST para las acciones o peticiones relacionadas con la entidad Actividad Versión 2 (ADMIN)"),
                		new Tag(ACTIVIDAD_V3_TAG, "Controlador REST para las acciones o peticiones relacionadas con la entidad Actividad Versión 3 (ADMIN)"),
                		new Tag(ALERTA_V2_TAG, "Controlador REST para las peticiones asociadas con la entidad Alerta para la versión 2"),
                		new Tag(AMISTAD_V2_TAG, "Controlador REST para las peticiones asociadas con la entidad Amistad para la versión 2"),
                		new Tag(ANUNCIO_V2_TAG, "Controlador REST para las peticiones asociadas con la entidad Anuncio para la versión 2"),
						new Tag(EVENTO_CALENDARIO_V2_TAG, "Controlador REST para las peticiones asociadas con la entidad EventoCalendario para la versión 2"),
                		new Tag(MATERIA_V2_TAG, "Controlador REST para las peticiones asociadas con la entidad Materia para la versión 2"),
						new Tag(MATERIA_V3_TAG, "Controlador REST para las peticiones asociadas con la entidad Materia para la versión 3 (ADMIN)"),
                		new Tag(NIVEL_ESTUDIOS_V2_TAG, "Controlador REST para las peticiones asociadas con la entidad NivelEstudios para la versión 3 (ADMIN)"),
						new Tag(NIVEL_ESTUDIOS_V3_TAG, "Controlador REST para las peticiones asociadas con la entidad NivelEstudios para la versión 2"),
                		new Tag(TUTOR_V2_TAG, "Controlador REST para las peticiones asociadas con la entidad Tutor para la versión 2"),
						new Tag(CURSO_V2_TAG, "Controlador REST para las peticiones asociadas con la entidad Curso para la versión 2"),
						new Tag(LOGIN_TAG, "Controlador REST para la acción de Inicio de Sesión del Usuario"),
						new Tag(REGISTER_TAG, "Controlador REST para la acción de Registro del Usuario en la app"))
                .apiInfo(apiInfo())
				.securityContexts(Arrays.asList(securityContext()))
				.securitySchemes(Arrays.asList(apiKey()));                
                
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "API REST StudyCircle",
                "La API REST para la gestión e interacción con la app Android StudyCircle.",
                "v1",
                "Terms of service",
                new Contact("StudyCircle", "", "studycirclecontact@gmail.com"),
                "License of API", "API license URL", Collections.emptyList());
    }
    
	private ApiKey apiKey() {
		return new ApiKey("JWT", "Authorization", "header");
	}
	
	private SecurityContext securityContext() {
		return SecurityContext.builder().securityReferences(defaultAuth()).build();
	}
	
	private List<SecurityReference> defaultAuth() {
		AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
		AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
		authorizationScopes[0] = authorizationScope;
		return Arrays.asList(new SecurityReference("JWT", authorizationScopes));
	}
	    
}
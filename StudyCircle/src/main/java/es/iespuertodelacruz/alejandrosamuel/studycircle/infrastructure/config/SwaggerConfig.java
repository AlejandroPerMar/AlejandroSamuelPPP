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
	public static final String ACTIVIDAD_TAG = "Actividad";
	public static final String ALERTA_TAG = "Alerta";
	public static final String AMISTAD_TAG = "Amistad";
	public static final String ANUNCIO_TAG = "Anuncio";
	public static final String EVENTO_TAG = "Evento de calendario";
	public static final String MATERIA_TAG = "Materia";
	public static final String MATERIA_ALUMNO_TAG = "MateriaAlumno";
	public static final String MATERIA_TUTOR_TAG = "MateriaTutor";
	public static final String MENSAJE_CHAT_TAG = "MensajeChat";
	public static final String NIVEL_ESTUDIOS_TAG = "NivelEstudios";
	public static final String ROL_TAG = "Rol";
	public static final String TARIFA_TAG = "Tarifa";
	public static final String TUTOR_TAG = "Tutor";
	public static final String USUARIO_TAG = "Usuario";
	
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary"))
                .paths(PathSelectors.any())
                .build()
                .tags(new Tag(ALUMNO_TAG, "Controlador REST para las acciones relacionadas con las entidades Alumno"),
                		new Tag(ACTIVIDAD_TAG, ""),
                		new Tag(ALERTA_TAG, ""),
                		new Tag(AMISTAD_TAG, ""),
                		new Tag(ANUNCIO_TAG, ""),
                		new Tag(EVENTO_TAG, ""),
                		new Tag(MATERIA_TAG, ""),
                		new Tag(MATERIA_ALUMNO_TAG, ""),
                		new Tag(MATERIA_TUTOR_TAG, ""),
                		new Tag(MENSAJE_CHAT_TAG, ""),
                		new Tag(NIVEL_ESTUDIOS_TAG, ""),
                		new Tag(ROL_TAG, ""),
                		new Tag(TARIFA_TAG, ""),
                		new Tag(TUTOR_TAG, ""),
                		new Tag(USUARIO_TAG, ""))
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
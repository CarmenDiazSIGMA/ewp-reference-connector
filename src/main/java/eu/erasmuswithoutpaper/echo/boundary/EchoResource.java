package eu.erasmuswithoutpaper.echo.boundary;

import eu.erasmuswithoutpaper.api.echo.Response;
import eu.erasmuswithoutpaper.organization.entity.Institution;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Stateless
@Path("echo")
public class EchoResource {
    @PersistenceContext(unitName = "connector")
    EntityManager em;

    @GET
    @Produces(MediaType.APPLICATION_XML)
    public Response echoGet(@QueryParam("echo") List<String> echo) {
        return echo(echo);
    }

    @POST
    @Produces(MediaType.APPLICATION_XML)
    public Response echoPost(@FormParam("echo") List<String> echo) {
        return echo(echo);
    }

    public Response echo(List<String> echo) {
        Response response = new Response();
        echo.stream().forEach(e -> response.getEcho().add(e));

        List<Institution> institutionList = em.createNamedQuery(Institution.findAll).getResultList();
        institutionList.stream().forEach(i -> response.getHeiId().add(i.getInstitutionId()));
        
        return response;
    }
}

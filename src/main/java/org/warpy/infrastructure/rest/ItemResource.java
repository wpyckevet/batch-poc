package org.warpy.infrastructure.rest;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.UUID;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.warpy.infrastructure.repositories.TestItemRepository;
import org.warpy.infrastructure.rest.mapper.ItemDTOMapper;

@Path("item")
@RequiredArgsConstructor(onConstructor = @__({ @Inject }))
@Slf4j
public class ItemResource {

    private final TestItemRepository testItemRepository;
    private final ItemDTOMapper mapper;

    @GET
    @Produces(APPLICATION_JSON)
    public Response getItem(@QueryParam("id") long id) {
        var item = testItemRepository.findById(id);
        var itemDTO = mapper.inverseMap(item);
        return Response.ok(itemDTO).build();
    }

    @POST
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    @Transactional
    public Response createItem(ItemDTO itemDTO) throws URISyntaxException {
        var item = mapper.map(itemDTO);
        item.setGuid(UUID.randomUUID());
        log.info(item.getGuid().toString());
        testItemRepository.persistAndFlush(item);
        var uri = new URI("http://localhost:8080/item?id=" + item.id);
        return Response.created(uri).entity(mapper.inverseMap(item)).build();
    }
}

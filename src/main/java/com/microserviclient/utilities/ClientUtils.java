package com.microserviclient.utilities;

import com.microserviclient.model.entity.ClientEntity;
import com.microserviclient.model.request.CreateClientRequest;
import com.microserviclient.model.request.UpdateClientRequest;




public interface ClientUtils {

    Boolean uniqueValuesDuplicity(ClientEntity clientA, ClientEntity clientB);
    ClientEntity clientCreateRequestToClient(CreateClientRequest clientDTO);
    ClientEntity clientUpdateRequestToClient(UpdateClientRequest clientDTO);
    CreateClientRequest clientToClientCreateRequest(ClientEntity client);
    UpdateClientRequest clientToClientUpdateRequest(ClientEntity client);
}

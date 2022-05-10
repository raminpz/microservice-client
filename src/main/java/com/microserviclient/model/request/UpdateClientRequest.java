package com.microserviclient.model.request;

import com.microserviclient.model.dto.ClientType;
import com.microserviclient.model.dto.EnterpriseInfo;
import com.microserviclient.model.dto.PersonInfo;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class UpdateClientRequest {

    private String id;
    private ClientType clientType;
    private String status;
    private PersonInfo personInfo;
    private EnterpriseInfo enterpriseInfo;
}

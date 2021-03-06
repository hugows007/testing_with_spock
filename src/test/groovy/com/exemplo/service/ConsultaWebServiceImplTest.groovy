package com.exemplo.service

import com.exemplo.domain.ObjectRequest
import com.exemplo.exceptions.ApiExceptions
import org.springframework.http.HttpStatus
import org.springframework.web.client.HttpClientErrorException
import org.springframework.web.client.RestTemplate
import spock.lang.Specification

class ConsultaWebServiceImplTest extends Specification {

    def "test callWebService"() {
        given:
            def objectRequest = new ObjectRequest(1215465654)
            def  restTemplate = Mock(RestTemplate){
                postForObject(_, _, _) >>  { throw new HttpClientErrorException(HttpStatus.BAD_REQUEST) }
            }
        def consulta = new ConsultaWebServiceImpl(restTemplate)
        when:
        consulta.callWebService(objectRequest)
        then:
        def e = thrown(ApiExceptions)
    }
}

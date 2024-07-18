package com.igse.security;

import com.igse.exception.UnAuthorizedException;
import com.igse.service.JwtService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Slf4j
@Component
public class GateWayFilter extends AbstractGatewayFilterFactory<GateWayFilter.Config> {
    private final JwtService jwtService;

    public GateWayFilter(JwtService jwtService) {
        super(Config.class);
        this.jwtService=jwtService;
    }


    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {
            if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)){
                throw new UnAuthorizedException("Token not found");
            }
            String tokenInHeader = Objects.requireNonNull(exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION)).get(0);

            try {
                if (null != tokenInHeader && tokenInHeader.startsWith("Bearer ")){
                    String token = tokenInHeader.substring(7);
                    jwtService.validate(token);
                }else {
                    throw new UnAuthorizedException("Invalid JWT Token");
                }
            }catch (Exception ex){
                log.error(ex.getMessage());
                throw new UnAuthorizedException("UnAuthorized Access");
            }


            return chain.filter(exchange);
        });
    }

    public static class Config { }

}

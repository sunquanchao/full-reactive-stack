package com.thepracticaldeveloper.reactiveweb.router;

import com.thepracticaldeveloper.reactiveweb.handler.AllHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.nest;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

/**
 * @program: routerfunction-demo
 * @description: 统一的路由配置，将url和处理器关联起来
 * @author: 01
 * @create: 2018-10-05 13:58
 **/

@Configuration
public class AllRouters {

    //{
    //	"id":1,
    //	"name":"mackchao",
    //	"age":30
    //}
    @Bean
    public RouterFunction<ServerResponse> quoteRouter(AllHandler handler) {
        return nest(
                // 请求路径前缀，相当于我们 controller 类上面写的@RequestMapping("/route")
                path("/route"),
                // 获取所有用户接口，相当于我们在controller方法里写的@GetMapping("/findByContent")
                route(GET("/findByContent"), handler::findByContent)
                        // 添加用户接口
//                        .andRoute(POST("/save").and(accept(APPLICATION_JSON_UTF8)), handler::createUser)
//                        // 更新用户接口
//                        .andRoute(PUT("/update/{id}").and(accept(APPLICATION_JSON_UTF8)), handler::updateUser)
//                        // 删除用户接口
//                        .andRoute(DELETE("/del/{id}"), handler::deleteUserById)
        );
    }


	@Bean
	public RouterFunction<ServerResponse> quote2Router(AllHandler handler) {
		return nest(
				path("/route2"),
				route(GET("/findByContent"), handler::findByContent)
		);
	}


}

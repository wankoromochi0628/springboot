package com.example.demo.common.Controller;

import com.example.demo.common.Domain.Customer;
import com.example.demo.common.Service.CustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import java.net.URI;
import java.util.List;

@Api(description = "Customerに関するAPI")
@RestController
@RequestMapping("api/customers")
public class CustomerRestController {
    @Autowired
    CustomerService customerService;

    /**
     * コンソール上で表示：curl http://localhost:2222/api/customers/list
     * ブラウザ上で表示：http://localhost:2222/api/customers/list
     * swagger上で表示：localhost:2222/swagger-ui.html
     * @return　List<Customer>　シリアライズされて、HTTPレスポンスのボディに設定される（JavaオブジェクトはJSON形式でシリアライズ）
     */
    @ApiOperation(value = "Customerを全て取得"
                , notes = "Customerを全て取得して、Listで返却します"
                , response = Customer.class
                , responseContainer = "List")
    @GetMapping(path = "/list")
    List<Customer> getCUstomers() {
        List<Customer> customers = customerService.findAll();
        return customers;
    }

    /**
     * コンソール上で表示：curl http://localhost:2222/api/customers/page
     * コンソール上で表示：curl http://localhost:2222/api/customers/page?page=0&size=3
     * コンソール上で表示：curl http://localhost:2222/api/customers/page?page=1&size=3
     * ブラウザ上で表示：http://localhost:2222/api/customers/page
     * swagger上で表示：localhost:2222/swagger-ui.html
     *
     * @param pageable
     * @return
     *
     * 「リクエスト・パラメータ」に設定した「page」「size」がこの「Pageable」オブジェクトにマッピングされる。
     * パラメータが指定されなかった場合のデフォルト値を設定するため、「@PageableDefault」アノテーションを付ける。
     * 「@PageableDefault」アノテーションに「page」や「size」のデフォルト値を指定できるが、何も指定しなかった場合は、「page0」「size=20」が設定される。
     */
    @GetMapping(path = "/page")
    Page<Customer> getCustomers(@PageableDefault Pageable pageable) {
        Page<Customer> customers = customerService.findAll(pageable);
        return customers;
    }

    /**
     * ＊＊idには任意の数字を指定してくだい
     * コンソール上で表示：curl http://localhost:2222/api/customers/{id}
     * ブラウザ上で表示：http://localhost:2222/api/customers/{id}
     * swagger上で表示：localhost:2222/swagger-ui.html
     *
     * @param id
     * @return　Customer
     *
     * path = "{id}"
     * path属性に相対パスを設定する。
     * パス中の「プレースホルダ」に指定したパラメータは、メソッドの引数で取得できる。
     * 対象の引数に「@PathVariable」アノテーションを付ける。
     */
    @GetMapping(path = "{id}")
    Customer getCustomer(@PathVariable Integer id){
        Customer customer = customerService.findOne(id);
        return customer;
    }

    /**
     * POSTでアクセスすると、実行される
     * ブラウザ上で表示：http://localhost:2222/api/customers TODO 修正
     * swagger上で表示：localhost:2222/swagger-ui.html
     *
     * @param customer
     * @param uriBuilder
     * @return
     */
    @PostMapping
    ResponseEntity<Customer> postCustomers(@RequestBody Customer customer
                                            , UriComponentsBuilder uriBuilder) {
        Customer created = customerService.create(customer);
        URI location = uriBuilder.path("api/customers/{id}")
                                 .buildAndExpand(created.getId())
                                 .toUri();

        return ResponseEntity.created(location).body(created);
    }

    /**
     * コンソール上で表示：curl http://localhost:2222/api/customers/update/{id} -i -xpost -H "Content-Type: application/json" -d "{\"firstName\":\"Nobio\",\"lastName\":\"\Nobi\"}"
     * ブラウザ上で表示：http://localhost:2222/api/customers/update/{id}
     * swagger上で表示：localhost:2222/swagger-ui.html
     *
     * @param id
     * @param customer
     * @return
     */
    @PostMapping(path = "/update/{id}")
    Customer putCustomer(@PathVariable Integer id
                        , @RequestBody Customer customer) {
        customer.setId(id);
        return customerService.update(customer);
    }

    /**
     * コンソール上で表示：curl http://localhost:2222/api/customers/delete/{id} -i -XDELETE
     * ブラウザ上で表示：http://localhost:2222/api/customers/delete/{id}
     * swagger上で表示：localhost:2222/swagger-ui.html
     *
     * @ResponseStatus(HttpStatus.NO_CONTENT) 204 No Contentを返す
     * @param id
     */
    @DeleteMapping(path = "/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteCustomer(@PathVariable Integer id) {
        customerService.delete(id);
    }
}

package boot.set.test.sik4.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import boot.set.test.sik4.domain.TestVO;
import boot.set.test.sik4.service.Sik4Service;

@CrossOrigin
@RestController
public class Sik4Controller {
	@Autowired
	Sik4Service sik4Service;
	
	@GetMapping("/test")
	public @ResponseBody ResponseEntity<?> test() {
		Map<String,Object> resultMap = new HashMap<>();
		List<TestVO> resultList = sik4Service.test();
		resultMap.put("resultList", resultList);

		return new ResponseEntity<List>(resultList,HttpStatus.OK);

	}
	@RequestMapping("/")
	public String home() {
		return "hello";
	}
}

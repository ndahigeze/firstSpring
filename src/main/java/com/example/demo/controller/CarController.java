package com.example.demo.controller;


import com.example.demo.domain.Car;
import com.example.demo.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cars")
public class CarController {
    @Autowired
   private CarRepository carRepository;


   @RequestMapping(path = "/save",method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE,produces =MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Object> create(@RequestBody Car car){
       carRepository.save(car);
      return new ResponseEntity<Object>(car,HttpStatus.OK);
  }



    @RequestMapping(path = "/update/{id}",method = RequestMethod.PUT,consumes = MediaType.APPLICATION_JSON_VALUE,produces =MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> update(@RequestBody Car car, @PathVariable("id")int id){

       car.setId(id);
       carRepository.save(car);
        return new ResponseEntity<Object>(car,HttpStatus.OK);
    }


    @RequestMapping(path = "/delete/{id}",method = RequestMethod.DELETE,consumes = MediaType.APPLICATION_JSON_VALUE,produces =MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> delete(@PathVariable("id") int id){
        Car c=carRepository.getOne(id);
        carRepository.delete(c);
        return new ResponseEntity<Object>("Done",HttpStatus.OK);
    }


    @RequestMapping(path = "",method = RequestMethod.GET,produces =MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> findAll(){

        return new ResponseEntity<Object>(carRepository.findAll(),HttpStatus.OK);
    }

    @RequestMapping(path = "/{id}",method = RequestMethod.GET,produces =MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> findOne(@PathVariable("id") int id){
       Car car=carRepository.getOne(id);
        return new ResponseEntity<Object>(car,HttpStatus.OK);
    }

}

package com.rabbitmq.demo.producer;


import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
//import org.springframework.util.Assert;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

@Component
public class Producer {

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Value("${rabbitmq.queue}")
    private String queueName;

    @Value("${rabbitmq.exchange}")
    private String exchange;

    @Value("${rabbitmq.routingKey}")
    private String routingKey;

    List<Activity> activities=new ArrayList<>();


    public void produceMsg(){

        String file="C://Users/Cristi/Desktop/Activity.txt";
        try(Stream<String> stream= Files.lines(Paths.get(file)))
        {
            stream.forEach(line->
            {
                SimpleDateFormat formatter =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                long date1Mili=0,date2Mili=0;
                int id=1;
                String  activity;
                String[] v = line.split("\t\t");
                Date date1= null;
                try {
                    date1 = formatter.parse(v[0]);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                Date date2= null;
                try {
                    date2 = formatter.parse(v[1]);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                date1Mili=date1.getTime();
                date2Mili=date2.getTime();
                // System.out.println("DateStart"+date1Mili + "DateEnd:" +date2Mili);
                activity=v[2];
                Activity activ=new Activity(id,date1Mili,date2Mili,activity);
                activities.add(activ);
                amqpTemplate.convertAndSend(exchange,routingKey,activ);
                 System.out.println("Patient_id:" + activ.getPatient_id() +" StartTime:"+activ.getStartTime() + " EndTime:" + activ.getEndTime() + " Name:" + activ.getName());

                //try {
                  //  Thread.sleep(1000);
                //} catch (InterruptedException e) {
                  //  e.printStackTrace();
                //}
            });
        }catch(IOException e)
        {
            System.out.println("Eroare");
        }
    }

   // public void afisare()
    //{
      //  System.out.println("Start_time\t\t\t End_time\t\t\t Activity");
       // activities.forEach(md->System.out.println(md.getStartTime()+" "+md.getEndTime()+" "+md.getName()));
   // }



   // public void produceMsg(List<Activity> activities){
     //   amqpTemplate.convertAndSend(exchange,routingKey,activities);
       // System.out.println("Send msg="+activities);

    //}
    }

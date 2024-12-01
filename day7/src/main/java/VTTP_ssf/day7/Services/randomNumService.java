package VTTP_ssf.day7.Services;

import java.security.SecureRandom;
import java.util.Random;

import org.springframework.stereotype.Service;

@Service
public class randomNumService {
    private Random rnd = new SecureRandom();

    public int getRandom(int bound){
        return rnd.nextInt(bound);
    }

    public int getRandom(){
        return rnd.nextInt(100);
    }
}

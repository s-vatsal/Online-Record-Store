package edu.trs.bootstrap;

import edu.trs.model.Album;
import edu.trs.model.ApplicationUser;
import edu.trs.repository.AlbumRepository;
import edu.trs.repository.ApplicationUserRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.GregorianCalendar;

@Component
public class DatabaseLoader implements ApplicationListener<ContextRefreshedEvent> {

    private final AlbumRepository albumRepository;
    private final ApplicationUserRepository applicationUserRepository;

    public DatabaseLoader(AlbumRepository albumRepository, ApplicationUserRepository applicationUserRepository) {
        this.albumRepository = albumRepository;
        this.applicationUserRepository = applicationUserRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        System.out.println("Spring app runs");

        albumRepository.addAlbum(new Album("DAMN.", "Kendrick Lamar",
                new GregorianCalendar(2017, Calendar.APRIL, 1).getTime(),
                "HipHop", 14, 9.99));

        albumRepository.addAlbum(new Album("ASTROWORLD", "Travis Scott",
                new GregorianCalendar(2018, Calendar.AUGUST, 3).getTime(),
                "HipHop/Rap", 17, 9.99));

        albumRepository.addAlbum(new Album("Shoot For The Stars, Aim For The Moon", "Pop Smoke",
                new GregorianCalendar(2020, Calendar.JULY, 3).getTime(),
                "HipHop/Drill/R&B", 34, 9.99));

        albumRepository.addAlbum(new Album("JACKBOYS", "JACKBOYS/Travis Scott",
                new GregorianCalendar(2019, Calendar.DECEMBER, 27).getTime(),
                "HipHop/Trap", 7, 9.99));

        albumRepository.addAlbum(new Album("Goodbye & Good Riddance", "Juice Wrld",
                new GregorianCalendar(2018, Calendar.MAY, 23).getTime(),
                "HipHop/Rap", 15, 9.99));

        albumRepository.addAlbum(new Album("Rodeo", "Travis Scott",
                new GregorianCalendar(2015, Calendar.SEPTEMBER, 4).getTime(),
                "HipHop/Trap", 16, 9.99));

        albumRepository.addAlbum(new Album("Tenet", "Ludwig Goransson",
                new GregorianCalendar(2020, Calendar.SEPTEMBER, 3).getTime(),
                "Instrumental", 20, 9.99));

        albumRepository.addAlbum(new Album("NOT ALL HEROES WEAR CAPES", "Metro Boomin",
                new GregorianCalendar(2018, Calendar.NOVEMBER, 2).getTime(),
                "HipHop/Trap", 13, 9.99));

        albumRepository.addAlbum(new Album("KIRK", "DaBaby",
                new GregorianCalendar(2019, Calendar.SEPTEMBER, 27).getTime(),
                "HipHop/Trap", 13, 9.99));

        albumRepository.addAlbum(new Album("The Dark Side of the Moon", "Pink Floyd",
                new GregorianCalendar(1973, Calendar.MARCH, 1).getTime(),
                "Rock", 9, 9.99));

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        ApplicationUser user1 = new ApplicationUser("admin", encoder.encode("secretpass"), true);
        ApplicationUser user2 = new ApplicationUser("fred", encoder.encode("123456"), false);
        applicationUserRepository.addUser(user1);
        applicationUserRepository.addUser(user2);



    }
}

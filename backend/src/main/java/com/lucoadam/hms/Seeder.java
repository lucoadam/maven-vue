package com.lucoadam.hms;

import com.lucoadam.hms.configuration.HMSProperties;
import com.lucoadam.hms.metas.Meta;
import com.lucoadam.hms.metas.MetaRepository;
import com.lucoadam.hms.metas.Year;
import com.lucoadam.hms.metas.YearRepository;
import com.lucoadam.hms.users.User;
import com.lucoadam.hms.users.UserRepository;
import org.slf4j.LoggerFactory;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class Seeder {
    private static final Logger logger = LoggerFactory.getLogger(Seeder.class);

    @Autowired
    UserRepository userRepository;

    @Autowired
    MetaRepository metaRepository;

    @Autowired
    YearRepository yearRepository;

    @Autowired
    HMSProperties hmsProperties;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @EventListener
    public void seed(ContextRefreshedEvent event){
        seedAdminUser();
        seedSuperUser();
        seedMetaData();
        seedCurrentYear();
    }

    private void seedAdminUser() {
        String sql = "SELECT username FROM users U WHERE U.username = 'admin' LIMIT 1";

        List<User> u = jdbcTemplate.query(sql, (resultSet, rowNum) -> null);

        if (u == null || u.size() <= 0) {

            User user = new User();

            user.setName("Admin");

            user.setUsername("admin");

            //password -> admin
            user.setPassword("$2a$08$lDnHPz7eUkSi6ao14Twuau08mzhWrL4kyZGGU5xfiGALO/Vxd5DOi");

            user.setRole("ROLE_ADMIN");

            userRepository.save(user);

            logger.info("Users Seeded");

        } else {

            logger.info("Users Seeding Not Required");

        }
    }

    private void seedSuperUser() {
        String sql = "SELECT username FROM users U WHERE U.username = 'superuser' LIMIT 1";

        List<User> u = jdbcTemplate.query(sql, (resultSet, rowNum) -> null);

        if (u == null || u.size() <= 0) {

            User user = new User();

            user.setName("I am Superuser");

            user.setUsername("superuser");

            //password -> admin
            user.setPassword("$2a$08$lDnHPz7eUkSi6ao14Twuau08mzhWrL4kyZGGU5xfiGALO/Vxd5DOi");

            user.setRole("ROLE_ADMIN");

            userRepository.save(user);

            logger.info("Users Seeded");

        } else {

            logger.info("Users Seeding Not Required");

        }


    }

    private void seedCurrentYear() {
        Optional<Year> year = yearRepository.findById(hmsProperties.getCurrentYear());

        if (!year.isPresent()) {
            Year currentYear = new Year();
            currentYear.setYear(hmsProperties.getCurrentYear());
            yearRepository.save(currentYear);
        }
    }

    private void seedMetaData() {

        if (metaRepository.findByKey("is_installed") == null) {
            Meta isInstalled = new Meta();
            isInstalled.setKey("is_installed");
            isInstalled.setValue("0");
            metaRepository.save(isInstalled);
        }


        if (metaRepository.findByKey("municipal_name") == null) {
            Meta meta = new Meta();
            meta.setKey("municipal_name");
            meta.setValue("Please set name from settings");
            metaRepository.save(meta);
        }


        if (metaRepository.findByKey("two_tab_based_entry") == null) {
            Meta meta = new Meta();
            meta.setKey("two_tab_based_entry");
            meta.setValue("true");
            metaRepository.save(meta);
        }


        if (metaRepository.findByKey("centre_based_entry") == null) {
            Meta meta = new Meta();
            meta.setKey("centre_based_entry");
            meta.setValue("true");
            metaRepository.save(meta);
        }


        if (metaRepository.findByKey("municipal_office_name") == null) {
            Meta meta = new Meta();
            meta.setKey("municipal_office_name");
            meta.setValue("नगर कार्यपालिकाको कार्यालय");
            metaRepository.save(meta);
        }



        if (metaRepository.findByKey("student_entry_lock_date") == null) {
            Meta meta = new Meta();
            meta.setKey("student_entry_lock_date");
            meta.setValue("");
            metaRepository.save(meta);
        }

        if (metaRepository.findByKey("practical_entry_lock_date") == null) {
            Meta meta = new Meta();
            meta.setKey("practical_entry_lock_date");
            meta.setValue("");
            metaRepository.save(meta);
        }


        if (metaRepository.findByKey("result_published") == null) {
            Meta meta = new Meta();
            meta.setKey("result_published");
            meta.setValue("");
            metaRepository.save(meta);
        }


        if (metaRepository.findByKey("manual_symbol_number") == null) {
            Meta meta = new Meta();
            meta.setKey("manual_symbol_number");
            meta.setValue("false");
            metaRepository.save(meta);
        }


        if (metaRepository.findByKey("th_marks_entry_school_side") == null) {
            Meta meta = new Meta();
            meta.setKey("th_marks_entry_school_side");
            meta.setValue("false");
            metaRepository.save(meta);
        }


        if (metaRepository.findByKey("supp_marks_entry_school_side") == null) {
            Meta meta = new Meta();
            meta.setKey("supp_marks_entry_school_side");
            meta.setValue("false");
            metaRepository.save(meta);
        }


        if (metaRepository.findByKey("marksheet_type") == null) {
            Meta meta = new Meta();
            meta.setKey("marksheet_type");
            meta.setValue("default");
            metaRepository.save(meta);
        }


        if (metaRepository.findByKey("province_text") == null) {
            Meta meta = new Meta();
            meta.setKey("province_text");
            meta.setValue("Pradesh 3");
            metaRepository.save(meta);
        }


        if (metaRepository.findByKey("municipal_office_location") == null) {
            Meta meta = new Meta();
            meta.setKey("municipal_office_location");
            meta.setValue("Melamchi, Sindupalchok");
            metaRepository.save(meta);
        }


        if (metaRepository.findByKey("symbol_listing_report_title") == null) {
            Meta meta = new Meta();
            meta.setKey("symbol_listing_report_title");
            meta.setValue("Please change from settings;please use semi colon for new line");
            metaRepository.save(meta);
        }


        if (metaRepository.findByKey("marksheet_issue_date") == null) {
            Meta meta = new Meta();
            meta.setKey("marksheet_issue_date");
            meta.setValue("Please change");
            metaRepository.save(meta);
        }




        if (metaRepository.findByKey("supp_minimum_grade") == null) {
            Meta meta = new Meta();
            meta.setKey("supp_minimum_grade");
            meta.setValue("2.8");
            metaRepository.save(meta);
        }




        if (metaRepository.findByKey("supp_at_max_subject") == null) {
            Meta meta = new Meta();
            meta.setKey("supp_at_max_subject");
            meta.setValue("2");
            metaRepository.save(meta);
        }




        if (metaRepository.findByKey("use_supp_marks_for_calculation") == null) {
            Meta meta = new Meta();
            meta.setKey("use_supp_marks_for_calculation");
            meta.setValue("2");
            metaRepository.save(meta);
        }


        if (metaRepository.findByKey("operating_year") == null) {
            Meta meta = new Meta();
            meta.setKey("operating_year");
            meta.setValue("75");
            metaRepository.save(meta);
        }

    }
}

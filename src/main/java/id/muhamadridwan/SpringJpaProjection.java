package id.muhamadridwan;

import id.muhamadridwan.model.Dosen;
import id.muhamadridwan.repository.QueryDslJpaEnhancedRepositoryImpl;
import id.muhamadridwan.service.DosenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableJpaRepositories(
        repositoryBaseClass = QueryDslJpaEnhancedRepositoryImpl.class,
        basePackages = {"id.muhamadridwan.repository"})
public class SpringJpaProjection implements CommandLineRunner {

    @Autowired
    private DosenService dosenService;

    @Override
    public void run(String... strings) throws Exception {
        List<Dosen> dosens = new ArrayList<>();

        Dosen dosen1 = new Dosen();
        dosen1.setNama("Muhamad Ridwan");
        dosen1.setAlamat("Purwakarta");

        Dosen dosen2 = new Dosen();
        dosen2.setNama("Ali Samsudin");
        dosen2.setAlamat("Jakarta");

        Dosen dosen3 = new Dosen();
        dosen3.setNama("Moyam Marangka");
        dosen3.setAlamat("Kalimantan");

        Dosen dosen4 = new Dosen();
        dosen4.setNama("Mamui Hambigu");
        dosen4.setAlamat("Irian Jaya");

        Dosen dosen5 = new Dosen();
        dosen5.setNama("Tongseng Syarifudin");
        dosen5.setAlamat("Bandung");

        Dosen dosen6 = new Dosen();
        dosen6.setNama("Kemal Atturk");
        dosen6.setAlamat("Bogor");

        Dosen dosen7 = new Dosen();
        dosen7.setNama("Jajang");
        dosen7.setAlamat("Karawang");

        Dosen dosen8 = new Dosen();
        dosen8.setNama("Somad Fudrawa");
        dosen8.setAlamat("Denpasar");

        Dosen dosen9 = new Dosen();
        dosen9.setNama("Moda Jukari");
        dosen9.setAlamat("Sulawesi");

        Dosen dosen10 = new Dosen();
        dosen10.setNama("Kibul Angin-angin");
        dosen10.setAlamat("Batak");

        dosens.add(dosen1);
        dosens.add(dosen2);
        dosens.add(dosen3);
        dosens.add(dosen4);
        dosens.add(dosen5);
        dosens.add(dosen6);
        dosens.add(dosen7);
        dosens.add(dosen8);
        dosens.add(dosen9);
        dosens.add(dosen10);

        dosenService.saveDosen(dosens);

    }

    public static void main(String[] args) {
        SpringApplication.run(SpringJpaProjection.class, args);
    }
}

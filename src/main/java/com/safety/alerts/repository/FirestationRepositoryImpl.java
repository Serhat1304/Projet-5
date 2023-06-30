    package com.safety.alerts.repository;

    import com.safety.alerts.model.Firestation;
    import lombok.extern.log4j.Log4j2;
    import org.springframework.stereotype.Repository;

    import java.util.*;

    @Repository
    @Log4j2
    public class FirestationRepositoryImpl implements IFirestationRepository {

        private List<Firestation> firestations;

        public FirestationRepositoryImpl() {
            this.firestations = new ArrayList<>();
        }

        @Override
        public List<Firestation> getAll() {
            return new ArrayList<>(firestations);
        }

        @Override
        public Firestation getFirestation(Integer station) {
            for (Firestation firestation : firestations) {
                if(Objects.equals(firestation.getStation(),station)) {
                    return firestation;
                }
            }
            return null;
        }

        @Override
        public Firestation addFirestation(Firestation firestation) {
            firestations.add(firestation);
            return firestation;
        }

        @Override
        public void deleteFirestation(Firestation firestation) {
            firestations.remove(firestation);
        }

        @Override
        public Firestation updateFirestation(Firestation firestation) {
            int index = firestations.indexOf(firestation);
            if(index != -1) {
                firestations.set(index, firestation);
                log.info("Updated firestation is successful, with station number : {}", firestation.getStation());
            }else{
                log.error("Updated firestation is failed, with station number : {}", firestation.getStation());
            }

            return firestation;
        }

        @Override
        public Firestation getFirestationByAddress(String address) {
            for(Firestation firestation : firestations) {
                if(Objects.equals(firestation.getAddress(), address)) {
                    log.info("Request getting firestation by address successful ");
                    return firestation;
                }
            }
            log.error("Request getting firestation by adsress failed");
            return null;
        }

    }
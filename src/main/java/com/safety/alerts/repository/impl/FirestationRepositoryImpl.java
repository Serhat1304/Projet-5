    package com.safety.alerts.repository.impl;

    import com.safety.alerts.model.Firestation;
    import com.safety.alerts.repository.IFirestationRepository;
    import com.safety.alerts.util.DataHolder;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Repository;
    import org.tinylog.Logger;

    import java.util.List;
    import java.util.Optional;

    @Repository
    
    public class FirestationRepositoryImpl implements IFirestationRepository {

        private List<Firestation> firestations;

        @Autowired
        public FirestationRepositoryImpl(DataHolder dataHolder){
            this.firestations = dataHolder.getResponse().getFirestations();
        }

        @Override
        public List<Firestation> getAll() {
            return firestations;
        }

        @Override
        public Firestation getFirestationByAddress(String address) {
            Optional<Firestation> optionalFirestation = firestations.stream()
                    .filter(firestation -> firestation.getAddress().equals(address))
                    .findFirst();
            return optionalFirestation.orElse(null);
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
            Firestation newFirestation = getFirestationByAddress(firestation.getAddress());
            if (newFirestation != null) {
                newFirestation.setStation(firestation.getStation());
                Logger.info("Update firestation is successful, with address : {}", firestation.getAddress());
                return newFirestation;
            }
            Logger.error("Update firestation failed, with address : {}", firestation.getAddress());
            return null;
        }

    }
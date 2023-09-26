package pr2;

import java.util.*;

public class AirRegisterImp implements AirRegister{
    Map<Company, List<Aircraft>> register = new HashMap<>();
    @Override
    public boolean addCompany(Company c) {
        if(register.containsKey(c)){
            return false;
        }
        register.put(c, new ArrayList<>());
        return true;
    }

    @Override
    public boolean registerAircraft(Company c, Aircraft a) {
        if(!register.containsKey(c)){
            throw new UnknownCompanyException("This Company is not already registered");
        }

        for(Company currentCompany: register.keySet()) {
            var aircrafts = register.get(currentCompany);
            if(aircrafts.contains(a)){
                if (currentCompany.equals(c))
                    return false;
                throw new DifferentCompanyException("");
            }
        }
        var aircrafts = register.get(c);
        aircrafts.add(a);

        return true;
    }

    @Override
    public Company findCompany(AircraftID id) {

        for(Company currentCompany: register.keySet()){
            var aircrafts = register.get(currentCompany);
            for(Aircraft aircraft: aircrafts){
                if(aircraft.toString().contains(id.toString())){
                    return currentCompany;
                }
            }

        }
        return null;
    }

    @Override
    public SortedSet<Aircraft> registeredAircrafts(Company c) {
        SortedSet<Aircraft> aircrafts = new TreeSet<>(new AircraftYearComparator());

        for(Company currentCompany: register.keySet()){
            if (currentCompany == c){
                var aircrafts_list = register.get(currentCompany);
                for(Aircraft aircraft: aircrafts_list){
                    aircrafts.add(aircraft);
                }
            }
        }
        return aircrafts;
    }

    @Override
    public SortedSet<Company> findCompanyByType(AircraftType t) {
        SortedSet<Company> companies = new TreeSet<>();

        for(Company currentCompany: register.keySet()){
            List<Aircraft> aircrafts = register.get(currentCompany);
            for(Aircraft aircraft: aircrafts){
                if(aircraft.toString().contains(t.toString())){
                    companies.add(currentCompany);
                }
            }
        }

        return companies;
    }
}

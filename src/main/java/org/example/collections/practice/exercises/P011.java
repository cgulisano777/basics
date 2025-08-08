package org.example.collections.practice.exercises;

import java.util.*;

import static org.example.collections.practice.exercises.P011.SlotType.*;

/**
 * Problem Statement:
 * Create an application to manage a parking lot in which you will have defined slots for cars, trucks, bicycles, bikes, etc...
 */
public class P011 implements Exercise {
    private final SlotProvider slotProvider = new SlotProvider();

    private final EnumMap<SlotType, Integer> parking1Attributes = new EnumMap<>(Map.of(
            CAR_SLOT, 5,
            TRUCK_SLOT, 2,
            BICYCLE_SLOT, 10,
            BIKE_SLOT, 8
    ));

    private final EnumMap<SlotType, Integer> parking2Attributes = new EnumMap<>(Map.of(
            CAR_SLOT, 20,
            TRUCK_SLOT, 15,
            BICYCLE_SLOT, 12,
            BIKE_SLOT, 40
    ));

    private final HashMap<String, Parking> parkingMap = new HashMap<>();

    @Override
    public void resolveExercise() {
        // Solution here:
        var parking1 = generateParking("Center", parking1Attributes);
        var parking2 = generateParking("Forest", parking2Attributes);

        parkingMap.put(parking1.name, parking1);
        parkingMap.put(parking2.name, parking2);
        displayParkings();

        System.out.println("Taking slots");
        var parkingCenter = retrieveParking("Center");
        if (parkingCenter != null) {
            handleSlot(parkingCenter, "A1", true);

            handleSlot(parkingCenter, "C1", true);
            handleSlot(parkingCenter, "C5", true);
            handleSlot(parkingCenter, "T2", true);
        }
        var parkingForest = retrieveParking("Forest");
        if (parkingCenter != null) {
            handleSlot(parkingForest, "K1", true);
            handleSlot(parkingForest, "K2", true);
            handleSlot(parkingForest, "K3", true);
            handleSlot(parkingForest, "K4", true);
            handleSlot(parkingForest, "K5", true);
            handleSlot(parkingForest, "K9", true);
            handleSlot(parkingForest, "K9", true);
        }
        var fakeParking = retrieveParking("Fake");
        handleSlot(fakeParking, "ZZ", true);

        displayParkings();

        System.out.println("Releasing some slots");
        if (parkingCenter != null) {
            handleSlot(parkingCenter, "A1", false);

            handleSlot(parkingCenter, "C5", false);
            handleSlot(parkingCenter, "T2", false);
        }
        if (parkingCenter != null) {
            handleSlot(parkingForest, "K3", false);
            handleSlot(parkingForest, "K4", false);
            handleSlot(parkingForest, "K9", false);
        }


        displayParkings();
    }

    /**
     * Makes a Slot unavailable
     */
    private void handleSlot(Parking parking, String identifier, boolean makeUnavailable) {
        if (null == parking) {
            System.out.println("Parking does not exist");
            return;
        }

        var slot = parking.slots.get(identifier);

        if (null == slot) {
            System.out.println("Slot does not exist: " + identifier);
            return;
        }

        if (makeUnavailable) {
            if (!slot.isAvailable()) {
                System.out.println("Slot is already Taken: " + identifier);
                return;
            }
            slot.takeSlot();
        } else {
            if (slot.isAvailable()) {
                System.out.println("Slot is already Free: " + identifier);
                return;
            }
            slot.releaseSlot();
        }

        parking.slots.replace(identifier, slot);
        parkingMap.replace(parking.name, parking);
    }
    
    public Parking retrieveParking(String name) {
        var parking = parkingMap.get(name);
        if (parking == null) System.out.println("Parking does not exist: " + name);
        return parking;
    }


    private Parking generateParking(String parkingName, EnumMap<SlotType, Integer> parkingAttributes) {
        var parkingSlots = new TreeMap<String, BaseSlot>();

        parkingAttributes.forEach((type, amount) -> {
            for (int i = 0; i < amount; i++) {
                var identifier = String.format(type.getAlphabet()+"%02d",i + 1);
                var slot = slotProvider.createSlot(type);
                parkingSlots.put(identifier, slot);
            }
        });

        return new Parking(parkingName, parkingSlots);
    }

    private void displayParkings() {
        parkingMap.values().forEach(this::displayParking);
    }

    private void displayParking(Parking parking) {
        System.out.println("\nParking - " + parking.name);
        parking.slots.forEach((key, value) ->
                System.out.println("Slot " + key + " | Type " + value.slotType + " | Available: " + value.isAvailable()));
    }

    // Classes for exercise
    record Parking(String name, TreeMap<String, BaseSlot> slots) {}

    abstract static class BaseSlot {
        private final SlotType slotType;
        private boolean available = true;
        private final int slotSize;

        BaseSlot(SlotType slotType, int slotSize) {
            this.slotType = slotType;
            this.slotSize = slotSize;
        }

        public boolean isAvailable() {
            return available;
        }

        public int getSlotSize() {
            return slotSize;
        }

        public void takeSlot() {
            available = false;
        }

        public void releaseSlot() {
            available = true;
        }

        public SlotType getSlotType() {
            return slotType;
        }
    }

    static class SlotProvider {
        public BaseSlot createSlot(SlotType slotType) {
            switch (slotType) {
                case CAR_SLOT -> {
                    return new CarSlot();
                }
                case TRUCK_SLOT -> {
                    return new TruckSlot();
                }
                case BIKE_SLOT -> {
                    return new BikeSlot();
                }
                case BICYCLE_SLOT -> {
                    return new BicycleSlot();
                }
            }

            return null;
        }
    }

    static class CarSlot extends BaseSlot {
        CarSlot() {
            super(CAR_SLOT, 10);
        }
    }

    static class TruckSlot extends BaseSlot {
        TruckSlot() {
            super(TRUCK_SLOT, 20);
        }
    }

    static class BikeSlot extends BaseSlot {
        BikeSlot() {
            super(BIKE_SLOT, 3);
        }
    }

    static class BicycleSlot extends BaseSlot {
        BicycleSlot() {
            super(BICYCLE_SLOT, 1);
        }
    }

    enum SlotType {
        CAR_SLOT('C'),
        TRUCK_SLOT('T'),
        BICYCLE_SLOT('B'),
        BIKE_SLOT('K');

        private final char alphabet;

        SlotType(char c) {
            alphabet = c;
        }

        public char getAlphabet() {
            return alphabet;
        }
    }
}

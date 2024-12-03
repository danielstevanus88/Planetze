package com.example.planetze.classes.AnnualEmission;

import com.example.planetze.classes.User;

import java.util.HashMap;

public class AnnualEmissionCalculator {
    public static double getTransportationResult(User user){
        HashMap<String, Integer> answers = user.getQuestionnaireAnswers();

        // Emission Factor for {-, Gasoline, Diesel, Hybrid, Electric, I don't know}
        double [] emissionFactor = {0, 0.24, 0.27, 0.16, 0.05, 0};

        // Kilometer driven
        double [] kilometerDriven = {5000, 10000, 15000, 20000, 25000, 35000};

        // Emission for Public Transportation
        double [][] emissionPublicTransportation = {
                {},
                {0, 0, 0, 0, 0, 0},                 // [1] Never
                {0, 246, 819, 1638, 3071, 4095},    // [2] Occasionally
                {0, 573, 1911, 3822, 7166, 9555},   // [3] Frequently
                {0, 573, 1911, 3822, 7166, 9555}    // [4] Always (same as Frequently)
        };

        // Emission for short haul flight
        double [] emissionShortHaul = {0, 0, 225, 600, 1200, 1800};

        // Emission for long haul flight
        double [] emissionLongHaul = {0, 0, 825, 2200, 4400, 6600};

        double result = 0;


        if(answers.get("q1") != null && answers.get("q1") == 1){ // Yes: use car
            result += emissionFactor[answers.get("q2")] * kilometerDriven[answers.get("q3")];
        }

        result += emissionPublicTransportation[answers.get("q4")][answers.get("q5")];
        result += emissionShortHaul[answers.get("q6")];
        result += emissionLongHaul[answers.get("q7")];

        return result;
    }

    public static double getFoodResult(User user){
        HashMap<String, Integer> answers = user.getQuestionnaireAnswers();

        // Emission for Vegan
        double [] emissionVegan = {0, 1000, 500, 1500, 0};

        // Emission for Beef
        double [] emissionBeef = {0, 2500, 1900, 1300, 0};

        // Emission for Pork
        double [] emissionPork = {0, 1450, 860, 450, 0};

        // Emission for Chicken
        double [] emissionChicken = {0, 905, 600, 200, 0};

        // Emission for Fish
        double [] emissionFish = {0, 800, 500, 150, 0};

        // Emisison for Throwing Food
        double [] emisisonThrowFood = {0, 0, 23.4, 70.2, 140.4};

        double result = 0;

        if(answers.get("q8") <= 3){ // Yes: use car
            result += emissionVegan[answers.get("q8")];
        }
        else {
            result += emissionBeef[answers.get("q9a")];
            result += emissionPork[answers.get("q9b")];
            result += emissionChicken[answers.get("q9c")];
            result += emissionFish[answers.get("q9d")];
        }

        result += emisisonThrowFood[answers.get("q10")];

        return result;
    }

    public static double getConsumptionResult(User user){
        HashMap<String, Integer> answers = user.getQuestionnaireAnswers();

        // Emission for Clothes
        double [] emissionClothes = {0, 360, 120, 100, 5};

        // Reduction for Second Hand
        double [] reductionSecondHand = {0, 0.5, 0.7, 0};

        // Emission for Electronic Devices
        double [] emissionElectronic = {0, 0, 300, 600, 900, 1200};

        // Reduction for Recycle depend on clothes
        double [][] reductionRecycleDependOnClothes = {
                {},
                {0, 0, 54, 108, 180},   // Monthly
                {0, 0, 0, 0, 0},
                {0, 0, 15, 30, 0},      // Annually
                {0, 0, 0, 0, 0}
        };

        // Reduction for Recycle depend on device
        double [][] reductionRecycleDependOnDevice = {
                {0},
                {0, 0, 45, 60, 90},     // 1 Device
                {0, 0, 60, 120, 80},    // 2 Devices
                {0, 0, 90, 180, 270},   // 3 Devices
                {0, 0, 120, 240, 360}   // 4 Devices
        };

        double result = 0;

        result += emissionClothes[answers.get("q18")];
        result *= reductionSecondHand[answers.get("q19")];
        result += emissionElectronic[answers.get("q20")];

        result -= reductionRecycleDependOnClothes[answers.get("q18")][0];
        result -= reductionRecycleDependOnDevice[answers.get("q20")][answers.get("q21")];

        return result;
    }

    public static double getHousingResult(User user){
        double result = 0;
        HashMap<String, Integer> answers = user.getQuestionnaireAnswers();
        int houseType = answers.get("q11");
        int occupants = answers.get("q12");
        int houseArea = answers.get("q13");
        int energyType = answers.get("q14");
        int energyBill = answers.get("q15");
        int heatWaterEnergy = answers.get("q16");
        int useReuseableEnergy = answers.get("q17");

        if(houseType == 5) {
            // Other house type calculations is considered the same as townhouse
            houseType = 3;
        }

        if (heatWaterEnergy != energyType){
            result += 233;
        }

        if(useReuseableEnergy == 1){
            result  -= 6000;
        }

        if(useReuseableEnergy == 2){
            result -= 4000;
        }
        int [][][] arrayForCalculation = AnnualEmissionConstant.townHouseUnder1000;

        if(houseType == 2 && houseArea == 1){
            arrayForCalculation = AnnualEmissionConstant.detachedUnder1000;
        }
        if (houseType == 2 && houseArea == 2){
            arrayForCalculation = AnnualEmissionConstant.detached10002000;
        }
        if (houseType == 2 && houseArea == 3){
            arrayForCalculation = AnnualEmissionConstant.detached2000;
        }
        if(houseType == 2 && houseArea == 1){
            arrayForCalculation = AnnualEmissionConstant.semiUnder1000;
        }
        if (houseType == 3 && houseArea == 2){
            arrayForCalculation = AnnualEmissionConstant.semi10002000;
        }
        if (houseType == 3 && houseArea == 3){
            arrayForCalculation = AnnualEmissionConstant.semiOver2000;
        }
        if(houseType == 3 && houseArea == 1){
            arrayForCalculation = AnnualEmissionConstant.townHouseUnder1000;
        }
        if (houseType == 3 && houseArea == 2){
            arrayForCalculation = AnnualEmissionConstant.townHouse10002000;
        }
        if (houseType == 4 && houseArea == 3){
            arrayForCalculation = AnnualEmissionConstant.townhouseOver2000;
        }
        if(houseType == 4 && houseArea == 1){
            arrayForCalculation = AnnualEmissionConstant.apartmentUnder1000;
        }
        if (houseType == 4 && houseArea == 2){
            arrayForCalculation = AnnualEmissionConstant.apartment10002000;
        }
        if (houseType == 4 && houseArea == 3){
            arrayForCalculation = AnnualEmissionConstant.apartmentOver2000;
        }

        result += arrayForCalculation[energyBill][occupants][energyType];

        return result;


    }

    public static double getTotalEmission(User user){
        return(double) (getHousingResult(user) + getConsumptionResult(user) + getTransportationResult(user) + getFoodResult(user));
    }

}

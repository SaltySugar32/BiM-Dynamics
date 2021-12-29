package com.company.Simulation.SimulationFunctions;

import com.company.Simulation.SimulationVariables.SimulationGlobals;

public class WaveFrontComputations {

    /**
     * Вычисление напряжения Эйлера-Коши на стыке волн деформации
     */
    public static double computeTension(double lilDeformations)
    {
        if (0 > lilDeformations)
        {
            //return \lambda + 2 * \mu + 2 * \nu
            return SimulationGlobals.getLameLambda() + 2 * SimulationGlobals.getLameMu() + 2 * SimulationGlobals.getCoefficientNu();
        } else if (0 < lilDeformations)
        {
            //return \lambda + 2 * \mu - 2 * \nu
            return SimulationGlobals.getLameLambda() + 2 * SimulationGlobals.getLameMu() - 2 * SimulationGlobals.getCoefficientNu();
        } else return 0;
    }

    /**
     * Вычисление характеристической скорости
     */
    public static double computeCharSpeed(double lilDeformations)
    {
        //return \sigma / \rho === (\lambda + 2 * \mu +- 2 * \nu) / \rho
        return computeTension(lilDeformations) / SimulationGlobals.getMaterialDensity();
    }

    /**
     * Формула для вычисления скорости новообразованной ударной волны
     */
    public static double computeNewWaveFrontSpeed(double speedLeft, double speedRight, double displacementPos, double displacementNeg)
    {
        //return sqrt( a^2 - (a^2 - b^2) * (U^+_,x) / (U^+_,x - U^-_,x) )
        return speedLeft * speedLeft - (Math.pow(speedLeft, 2) - Math.pow(speedRight, 2)) * (displacementPos) / (displacementPos - displacementNeg);
    }

    /**
     * U-,x = U+,x - ((a^2 - b^2) * U+,x) / (a^2 - G^2)
     */
    public static double computeWaveDisplacementNeg(double speedLeft, double speedRight, double displacementPos, double newSpeed)
    {
        return displacementPos - ((speedLeft - speedRight) * displacementPos) / (Math.pow(speedLeft, 2) * newSpeed);
    }

    /**
     * U+,x = U-,x * (a^2 - G^2) / (b^2 - G^2)
     */
    public static double computeWaveDisplacementPos(double speedLeft, double speedRight, double displacementNeg, double newSpeed)
    {
        return displacementNeg * (Math.pow(speedLeft, 2) - Math.pow(newSpeed, 2)) / (Math.pow(speedRight, 2) - Math.pow(newSpeed, 2));
    }

    //Вычисление системы уравнений из четырех элементов
    //Предыдущий = новый;
    //Новый = следующий.
    //Преобразуем два уравнения в системе так, чтобы они приняли следующий вид:
    //Предыдущий = новый:
    //  Равенство членов 0-й степени производной
    //  Равенство членов 1-й степени производной
    //Новый = следующий:
    //  Равенство членов 0-й степени производной
    //  Равенство членов 1-й степени производной

    //В данных системах имеем четыре неизвестные: A1_i, A2_i, A0_i, V_i
    //Выражаем из формулы поиска скорости волнового фронта U-,x === A1_i
    //Подставляем на соответствующее место выраженное уравнение (ручками нужно предварительно всё подготовить)
    //Таким образом получаем все четыре неизвестные

    //Вопросы остались, но хоть понять уже легче, что тут творится, блэт

    //Выполнено:
    //формулы U-,x и U+,x

    //Необходимо:
    //придумать, как работать с системой уравнений
}

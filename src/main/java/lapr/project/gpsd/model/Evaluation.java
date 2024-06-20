/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.gpsd.model;

import java.util.List;
import java.util.Objects;

import lapr.project.gpsd.registry.ExecutionOrderRegistry;
import lapr.project.gpsd.registry.ServiceProviderRegistry;

/**
 *
 * @author Utilizador
 */
public class Evaluation {

    private String sol;
    private String just;
    private boolean answer;
    /*
    private ExecutionOrder eo;
    private ExecutionOrderRegistry eor;
    private List<ExecutionOrder> leo;
    private ServiceProvider sp;
    private ServiceProviderRegistry spr;
     */

    /**
     * Empty Controller
     */
    public Evaluation() {
        this.answer = false;
        this.sol = null;
        this.just = null;
    }

    /**
     * Controller
     * @param answer
     */
    public Evaluation(boolean answer) {
        this.answer = answer;
        this.sol = "";
        this.just = "";
    }

    /**
     * Method to report the end of service
     * @param just
     * @param sol
     * @return true if answer is true or if answer is false and validates the justification and the solution and if adds the justification and the solution; return false if none of this ocoores
     */
    public boolean reportEndService(String just, String sol) {
        if (answer == false) {
            if (validateDesc(just, sol)) {
                addDesc(just, sol);
                return true;
            }
        }else{
            return true;
        }
        return false;

    }

    /**
     * Method to validate if the justification is null or if the solution is null
     * @param just
     * @param sol
     * @return just != null && sol != null
     */
    public boolean validateDesc(String just, String sol) {
        return just != null && sol != null;
    }

    /**
     * Method to add justification and solution
     * @param just
     * @param sol
     */
    public void addDesc(String just, String sol) {
        this.just = just;
        this.sol = sol;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Evaluation that = (Evaluation) o;
        return answer == that.answer &&
                Objects.equals(sol, that.sol) &&
                Objects.equals(just, that.just);
    }

}

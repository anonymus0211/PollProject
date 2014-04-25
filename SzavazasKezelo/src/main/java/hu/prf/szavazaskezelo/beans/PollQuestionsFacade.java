/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hu.prf.szavazaskezelo.beans;

import hu.prf.szavazaskezelo.entitites.PollQuestions;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author anonymus0211
 */
@Stateless
public class PollQuestionsFacade extends AbstractFacade<PollQuestions> {
    @PersistenceContext(unitName = "hu.prf_SzavazasKezelo_war_versionPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PollQuestionsFacade() {
        super(PollQuestions.class);
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hu.prf.szavazaskezelo.beans;

import hu.prf.szavazaskezelo.entitites.PollAnswers;
import hu.prf.szavazaskezelo.entitites.PollQuestions;
import hu.prf.szavazaskezelo.entitites.Polls;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author anonymus0211
 */
@Stateless
public class PollAnswersFacade extends AbstractFacade<PollAnswers> {
    @PersistenceContext(unitName = "hu.prf_SzavazasKezelo_war_versionPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PollAnswersFacade() {
        super(PollAnswers.class);
    }
    
    public PollQuestions findByPollQuestionId(Long pollQuestionId) {
        TypedQuery<PollQuestions> query = em.createNamedQuery("PollQuestions.findById",PollQuestions.class);
        return query.setParameter("id", pollQuestionId).getSingleResult();
    }
    
}

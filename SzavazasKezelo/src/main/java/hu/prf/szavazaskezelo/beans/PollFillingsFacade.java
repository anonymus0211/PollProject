/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hu.prf.szavazaskezelo.beans;

import hu.prf.szavazaskezelo.entitites.PollFillings;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author anonymus0211
 */
@Stateless
public class PollFillingsFacade extends AbstractFacade<PollFillings> {
    @PersistenceContext(unitName = "hu.prf_SzavazasKezelo_war_versionPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PollFillingsFacade() {
        super(PollFillings.class);
    }
    
}

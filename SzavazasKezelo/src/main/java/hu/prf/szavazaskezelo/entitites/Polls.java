/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hu.prf.szavazaskezelo.entitites;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Ano4Ever
 */
@Entity
@Table(name = "polls")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Polls.findAll", query = "SELECT p FROM Polls p"),
    @NamedQuery(name = "Polls.findById", query = "SELECT p FROM Polls p WHERE p.id = :id"),
    @NamedQuery(name = "Polls.findByPollTitle", query = "SELECT p FROM Polls p WHERE p.pollTitle = :pollTitle")})
public class Polls implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Size(max = 2147483647)
    @Column(name = "poll_title")
    private String pollTitle;
    @OneToMany(mappedBy = "pollId", fetch = FetchType.EAGER)
    private Set<PollQuestions> pollQuestionsSet;

    public Polls() {
    }

    public Polls(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPollTitle() {
        return pollTitle;
    }

    public void setPollTitle(String pollTitle) {
        this.pollTitle = pollTitle;
    }

    @XmlTransient
    public Set<PollQuestions> getPollQuestionsSet() {
        return pollQuestionsSet;
    }

    public void setPollQuestionsSet(Set<PollQuestions> pollQuestionsSet) {
        this.pollQuestionsSet = pollQuestionsSet;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Polls)) {
            return false;
        }
        Polls other = (Polls) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return  pollTitle;
    }
    
}

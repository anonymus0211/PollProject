/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hu.prf.szavazaskezelo.entitites;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ano4Ever
 */
@Entity
@Table(name = "poll_fillings")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PollFillings.findAll", query = "SELECT p FROM PollFillings p"),
    @NamedQuery(name = "PollFillings.findById", query = "SELECT p FROM PollFillings p WHERE p.id = :id")})
public class PollFillings implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @JoinColumn(name = "poll_question_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.EAGER)
    private PollQuestions pollQuestionId;
    @JoinColumn(name = "poll_answers_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.EAGER)
    private PollAnswers pollAnswersId;

    public PollFillings() {
    }

    public PollFillings(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PollQuestions getPollQuestionId() {
        return pollQuestionId;
    }

    public void setPollQuestionId(PollQuestions pollQuestionId) {
        this.pollQuestionId = pollQuestionId;
    }

    public PollAnswers getPollAnswersId() {
        return pollAnswersId;
    }

    public void setPollAnswersId(PollAnswers pollAnswersId) {
        this.pollAnswersId = pollAnswersId;
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
        if (!(object instanceof PollFillings)) {
            return false;
        }
        PollFillings other = (PollFillings) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hu.prf.szavazaskezelo.entitites.PollFillings[ id=" + id + " ]";
    }
    
}

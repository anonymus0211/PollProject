/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hu.prf.szavazaskezelo.entitites;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author anonymus0211
 */
@Entity
@Table(name = "poll_answers")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PollAnswers.findAll", query = "SELECT p FROM PollAnswers p"),
    @NamedQuery(name = "PollAnswers.findById", query = "SELECT p FROM PollAnswers p WHERE p.id = :id"),
    @NamedQuery(name = "PollAnswers.findByAnswer", query = "SELECT p FROM PollAnswers p WHERE p.answer = :answer")})
public class PollAnswers implements Serializable {
    @OneToMany(mappedBy = "pollAnswersId")
    private Collection<PollFillings> pollFillingsCollection;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Size(max = 2147483647)
    @Column(name = "answer")
    private String answer;
    @JoinColumn(name = "poll_question_id", referencedColumnName = "id")
    @ManyToOne
    private PollQuestions pollQuestionId;

    public PollAnswers() {
    }

    public PollAnswers(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public PollQuestions getPollQuestionId() {
        return pollQuestionId;
    }

    public void setPollQuestionId(PollQuestions pollQuestionId) {
        this.pollQuestionId = pollQuestionId;
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
        if (!(object instanceof PollAnswers)) {
            return false;
        }
        PollAnswers other = (PollAnswers) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hu.prf.szavazaskezelo.entitites.PollAnswers[ id=" + id + " ]";
    }

    @XmlTransient
    public Collection<PollFillings> getPollFillingsCollection() {
        return pollFillingsCollection;
    }

    public void setPollFillingsCollection(Collection<PollFillings> pollFillingsCollection) {
        this.pollFillingsCollection = pollFillingsCollection;
    }
    
}

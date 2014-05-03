package models;

import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Access(AccessType.FIELD)
public class Spieler extends Model {

    @Id
    public long id;
    @Constraints.Required
    @Constraints.MinLength(4)
    @Constraints.MaxLength(16)
    public String name;


}

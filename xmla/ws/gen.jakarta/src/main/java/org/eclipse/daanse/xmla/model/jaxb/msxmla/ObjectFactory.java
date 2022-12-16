
package org.eclipse.daanse.xmla.model.jaxb.msxmla;

import jakarta.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.eclipse.daanse.xmla.model.jaxb.msxmla package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.eclipse.daanse.xmla.model.jaxb.msxmla
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link NormTupleSet }
     * 
     */
    public NormTupleSet createNormTupleSet() {
        return new NormTupleSet();
    }

    /**
     * Create an instance of {@link NormTuplesType }
     * 
     */
    public NormTuplesType createNormTuplesType() {
        return new NormTuplesType();
    }

    /**
     * Create an instance of {@link NormTuplesType.NormTuple }
     * 
     */
    public NormTuplesType.NormTuple createNormTuplesTypeNormTuple() {
        return new NormTuplesType.NormTuple();
    }

    /**
     * Create an instance of {@link NormTupleSet.MembersLookup }
     * 
     */
    public NormTupleSet.MembersLookup createNormTupleSetMembersLookup() {
        return new NormTupleSet.MembersLookup();
    }

    /**
     * Create an instance of {@link NormTuplesType.NormTuple.MemberRef }
     * 
     */
    public NormTuplesType.NormTuple.MemberRef createNormTuplesTypeNormTupleMemberRef() {
        return new NormTuplesType.NormTuple.MemberRef();
    }

}

package neustar.registry.jtoolkit2.session;

/**
 * Describe the state of a transaction.  Initially, a transaction is
 * unprocessed.  See the description of {@link
 * neustar.registry.jtoolkit2.session.SessionManagerImpl#execute(Transaction[])}
 * for the state transitions.
 */
public enum TransactionState {
    UNPROCESSED,
    PROCESSED,
    RETRY,
    FATAL_ERROR
}


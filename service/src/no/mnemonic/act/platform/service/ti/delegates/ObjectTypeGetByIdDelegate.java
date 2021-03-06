package no.mnemonic.act.platform.service.ti.delegates;

import no.mnemonic.act.platform.api.exceptions.AccessDeniedException;
import no.mnemonic.act.platform.api.exceptions.AuthenticationFailedException;
import no.mnemonic.act.platform.api.exceptions.InvalidArgumentException;
import no.mnemonic.act.platform.api.exceptions.ObjectNotFoundException;
import no.mnemonic.act.platform.api.model.v1.ObjectType;
import no.mnemonic.act.platform.api.request.v1.GetObjectTypeByIdRequest;
import no.mnemonic.act.platform.service.contexts.SecurityContext;
import no.mnemonic.act.platform.service.ti.TiFunctionConstants;
import no.mnemonic.act.platform.service.ti.TiRequestContext;

public class ObjectTypeGetByIdDelegate extends AbstractDelegate {

  public static ObjectTypeGetByIdDelegate create() {
    return new ObjectTypeGetByIdDelegate();
  }

  public ObjectType handle(GetObjectTypeByIdRequest request)
          throws AccessDeniedException, AuthenticationFailedException, InvalidArgumentException, ObjectNotFoundException {
    SecurityContext.get().checkPermission(TiFunctionConstants.viewTypes);
    return TiRequestContext.get().getObjectTypeConverter().apply(fetchExistingObjectType(request.getId()));
  }

}

/*
 * (C) Copyright 2006-2011 Nuxeo SA (http://nuxeo.com/) and others.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Contributors:
 *     bstefanescu
 */
package org.nuxeo.ecm.core.event.script;

import javax.script.Bindings;
import javax.script.ScriptException;
import javax.script.SimpleBindings;

import org.nuxeo.ecm.core.api.NuxeoException;
import org.nuxeo.ecm.core.event.EventBundle;
import org.nuxeo.ecm.core.event.PostCommitEventListener;

/**
 * @author <a href="mailto:bs@nuxeo.com">Bogdan Stefanescu</a>
 */
public class ScriptingPostCommitEventListener implements PostCommitEventListener {

    protected final Script script;

    public ScriptingPostCommitEventListener(Script script) {
        this.script = script;
    }

    @Override
    public void handleEvent(EventBundle bundle) {
        Bindings bindings = new SimpleBindings();
        bindings.put("bundle", bundle);
        try {
            script.run(bindings);
        } catch (ScriptException e) {
            throw new NuxeoException("Failed to run script: " + script.getLocation(), e);
        }
    }

}

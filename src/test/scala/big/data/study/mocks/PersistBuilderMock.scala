package big.data.study.mocks

import big.data.study.persist.{PersistBuilder, Persist}
import org.scalatest.mock.MockitoSugar
import org.mockito.Mockito.when
import org.mockito.Matchers.anyString


trait PersistBuilderMock extends MockitoSugar {

  def mockBuilder (persist:Persist): PersistBuilder = {
     val persistBuilder = mock[PersistBuilder]
     when(persistBuilder.build(anyString)).thenReturn(persist)
     persistBuilder
  }

}
